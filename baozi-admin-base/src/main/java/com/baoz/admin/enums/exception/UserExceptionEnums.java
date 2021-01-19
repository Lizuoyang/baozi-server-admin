package com.baoz.admin.enums.exception;

import com.baoz.admin.exception.AbstractServiceException;
import lombok.AllArgsConstructor;

/**
 * @ClassName UserExceptionEnums
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/1/19 17:32
 **/
@AllArgsConstructor
public enum  UserExceptionEnums implements AbstractServiceException {
    USER_NOT_EXIST(100001, "用户不存在"),
    USER_LOGIN_PARAM_ERROR(100002,"登录参数错误"),
    USER_LOGIN_FAIL(100003,"用户名或密码错误"),
    ;
    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
