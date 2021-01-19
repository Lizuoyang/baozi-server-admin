package com.baoz.admin.enums.exception;

import com.baoz.admin.exception.AbstractServiceException;
import lombok.AllArgsConstructor;

/**
 * @ClassName TestExceptionEnums
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/1/19 13:59
 **/
@AllArgsConstructor
public enum TestExceptionEnums implements AbstractServiceException {
    TEST_EXCEPTION_ENUMS(000001, "测试异常枚举"),
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
