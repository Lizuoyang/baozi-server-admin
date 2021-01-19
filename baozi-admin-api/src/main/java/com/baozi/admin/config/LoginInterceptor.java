package com.baozi.admin.config;


import com.baoz.admin.constants.RedisKeyConstant;
import com.baozi.admin.RedisUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lizuoyang
 * @description 用户登录拦截器
 * @date 19:53 2020/12/9
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Order(Integer.MIN_VALUE + 1)
    private RedisUtil redisUtil;

    private List<String> excludeUrl;

    public LoginInterceptor(List<String> excludeUrl) {
        super();
        this.excludeUrl = excludeUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (excludeUrl.stream().anyMatch(urlitem -> antPathMatcher.match(urlitem, url))) {
            return true;
        }
        String headToken = request.getHeader("baoziToken");

        if (StringUtil.isEmpty(headToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        if (StringUtil.isNotEmpty(headToken)) {
            // 判断是否已经登录了
            if (!redisUtil.hasKey(RedisKeyConstant.SYS_USER_TOKEN + headToken)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
            return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
