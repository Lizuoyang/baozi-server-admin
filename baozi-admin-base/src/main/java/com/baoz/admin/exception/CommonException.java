package com.baoz.admin.exception;

import lombok.Data;

/**
 * @author lizuoyang
 * @description 统一抛出异常类
 * @date 10:36 2020/11/9
 **/
@Data
public class CommonException extends RuntimeException{
    private Integer code;
    private String errorMessage;

    public CommonException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public CommonException(AbstractServiceException exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }
}
