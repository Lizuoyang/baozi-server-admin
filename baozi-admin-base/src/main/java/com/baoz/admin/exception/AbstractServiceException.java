package com.baoz.admin.exception;

/**
 * @author lizuoyang
 * @description 异常枚举统一实现类
 * @date 20:14 2020/9/20
 **/
public interface AbstractServiceException {
    /**
     * 错误编码
     * @return
     */
    Integer getCode();

    /**
     * 错误提示
     * @return
     */
    String getMessage();
}
