package com.baozi.admin.vo;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysRoleVo implements Serializable {
    private static final long serialVersionUID = 2488077598118066973L;
    private String id;

    private String roleCode;

    private String roleName;

    private Integer roleSort;

    /**
     * 是否删除0:未删除。1：已删除
     */
    private Boolean isDelete;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
