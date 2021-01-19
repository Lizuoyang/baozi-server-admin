package com.baozi.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SystemUpdateDTO
 * @Description 系统管理 - 修改参数
 * @Author LiZuoYang
 * @Date 2020/12/17 16:12
 **/
@Data
public class SystemUpdateDTO implements Serializable {
    private static final long serialVersionUID = -6726887136532357753L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 系统编码
     */
    private String systemCode;
    /**
     * 排序
     */
    private Integer systemSort;
    /**
     * 是否删除： 0-使用中 1-已删除
     */
    private Boolean isDelete;

    /**
     * 操作人
     */
    private String actionBy;

}
