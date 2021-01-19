package com.baoz.admin.annation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author lizuoyang
 * @description 标注管理层注解
 * @date 15:21 2020/11/30
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Manager {
}
