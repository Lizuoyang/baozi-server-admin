package com.baozi.admin.config;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.baoz.admin.constants.IdPrefixConstant;
import com.baoz.admin.constants.RedisKeyConstant;
import com.baozi.admin.RedisUtil;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.entity.SysOperationLogDO;
import com.baozi.admin.entity.SysUserDO;
import com.baozi.admin.service.SysOperationLogService;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName OperationLogAop
 * @Description 使用aop切面记录请求日志
 * @Author LiZuoYang
 * @Date 2021/1/29 11:10
 **/
@Aspect
@Component
@Slf4j
public class OperationLogAop {
    @Autowired
    private SysOperationLogService logService;

    @Autowired
    private RedisUtil redisUtil;

    private static final String UNKNOWN = "unknown";

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.baozi.admin.config.OperationLog)")
    public void log() {

    }
    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        long startTime=System.currentTimeMillis();

        Object result=point.proceed();
        System.out.println("Response："+ JSONObject.toJSONString(result));
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));

        return result;
    }


    @Before("log()")
    public void beforeLog(JoinPoint point) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        String header = request.getHeader("User-Agent");
        String token = request.getHeader("baoziToken");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        String userInfoKey = RedisKeyConstant.SYS_USER_TOKEN + token;
        String userInfoJsonStr = redisUtil.get(userInfoKey);
        SysUserDO sysUser = JSONObject.parseObject(userInfoJsonStr, SysUserDO.class);
        Map<String, Object> params = getNameAndValue(point);

        SysOperationLogDO operationLog = SysOperationLogDO.builder()
                .id(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_LOG))
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ipAddress(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
                        point.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(JSONObject.toJSONString(params))
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(header)
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString())
                .createdTime(LocalDateTime.now())
                .createdBy(Objects.isNull(sysUser) ? (String) params.get("username") : sysUser.getLoginName())
                .operationDesc(getLogValue(point))
                .build();
        logService.save(operationLog);
    }

    /**
     * @author lizuoyang
     * @description 获取日志注解内容
     * @date 14:10 2021/1/29
     * @param joinPoint
     * @return java.lang.String
     **/
    private String getLogValue(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        OperationLog operationLog = method.getAnnotation(OperationLog.class);

        return operationLog.value();
    }

    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getNameAndValue(JoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

}
