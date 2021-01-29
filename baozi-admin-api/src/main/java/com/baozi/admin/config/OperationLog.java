package com.baozi.admin.config;

import java.lang.annotation.*;

/**
 * @author lizuoyang
 * @description 捕捉日志注解
 * @date 14:07 2021/1/29
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    String value() default "";
}
