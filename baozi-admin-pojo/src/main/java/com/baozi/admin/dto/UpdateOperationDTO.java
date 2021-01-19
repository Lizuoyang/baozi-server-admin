package com.baozi.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddOperationDTO
 * @Description 操作按钮添加参数类
 * @Author LiZuoYang
 * @Date 2021/1/11 14:25
 **/
@Data
public class UpdateOperationDTO implements Serializable {
    private static final long serialVersionUID = -3803015122439851546L;

    /**
     * 主键
     */
    private String id;

    /**
     * 操作编码
     */
    private String operationCode;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 排序
     */
    private Integer operationSort;

    /**
     * 操作人
     */
    private String actionBy;
}
