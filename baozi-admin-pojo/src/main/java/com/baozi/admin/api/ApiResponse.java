package com.baozi.admin.api;

import com.baoz.admin.enums.common.HttpStatusEnums;
import com.baoz.admin.exception.CommonException;
import lombok.Data;

/**
 * @ClassName ApiResponse
 * @Description 通用的 API 接口封装
 * @Author LiZuoYang
 * @Date 2021/1/19 14:24
 **/
@Data
public class ApiResponse {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回内容
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 无参构造函数
     */
    private ApiResponse() {

    }

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     */
    private ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return ApiResponse
     */
    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(HttpStatusEnums.OK, data);
    }

    /**
     * 构造一个成功不带数据的API返回
     *
     * @return ApiResponse
     */
    public static ApiResponse ofSuccess() {
        return ofStatus(HttpStatusEnums.OK, null);
    }

    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message 返回内容
     * @return ApiResponse
     */
    public static ApiResponse ofMessage(String message) {
        return of(HttpStatusEnums.OK.getCode(), message, null);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link HttpStatusEnums}
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(HttpStatusEnums status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link HttpStatusEnums}
     * @param data   返回数据
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(HttpStatusEnums status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t    异常
     * @param data 返回数据
     * @param <T>  {@link CommonException} 的子类
     * @return ApiResponse
     */
    public static <T extends CommonException> ApiResponse ofException(T t, Object data) {
        return of(t.getCode(), t.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t   异常
     * @param <T> {@link CommonException} 的子类
     * @return ApiResponse
     */
    public static <T extends CommonException> ApiResponse ofException(T t) {
        return ofException(t, null);
    }
}
