package com.baozi.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private LocalDate updatedTime;

    /**
     * 是否删除0:未删除。1：已删除
     */
    private Boolean isDelete;
}
