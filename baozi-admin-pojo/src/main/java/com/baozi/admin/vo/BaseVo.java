package com.baozi.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseVo
 * @Description 基础VO类
 * @Author LiZuoYang
 * @Date 2020/12/10 11:05
 **/
@Data
public class BaseVo implements Serializable {
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 是否删除0:未删除。1：已删除
     */
    private Boolean isDelete;
}
