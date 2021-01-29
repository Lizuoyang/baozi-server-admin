package com.baozi.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sys_operation_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysOperationLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 线程ID
     */
    private String threadId;

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 请求路径
     */
    private String url;

    /**
     * http方法 GET POST PUT DELETE PATCH
     */
    private String httpMethod;

    /**
     * 类方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 接口耗时
     */
    private Long timeCost;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * user-agent
     */
    private String userAgent;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 操作人
     */
    private String createdBy;


}
