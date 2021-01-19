package com.bao.admin;

import com.baoz.admin.exception.CommonException;
import com.baozi.admin.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lizuoyang
 * @description 全局异常统一处理
 * @date 10:39 2020/11/9
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse error(Exception exception) {
        log.info("进入统一异常拦截，异常类型：Exception");
        return ApiResponse.ofMessage(exception.getMessage());
    }

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ApiResponse err(CommonException exception) {
        log.info("进入统一异常拦截，异常类型：CommonException");
        return ApiResponse.ofException(exception);
    }
}
