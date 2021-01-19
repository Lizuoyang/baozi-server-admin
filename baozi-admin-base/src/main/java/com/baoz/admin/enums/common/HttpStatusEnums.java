package com.baoz.admin.enums.common;

import lombok.Getter;

/**
 * @ClassName HttpStatusEnums
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/1/19 14:27
 **/
@Getter
public enum  HttpStatusEnums {
    /**
     * 操作成功
     */
    OK(200, "操作成功"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    HttpStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
