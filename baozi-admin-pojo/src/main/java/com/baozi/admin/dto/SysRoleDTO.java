package com.baozi.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色，新增，修改入参dto
 */

@Data
public class SysRoleDTO implements Serializable {
    private static final long serialVersionUID = -8656969064439881771L;

    private Long id;

    private String roleCode;

    private String roleName;

    private Integer roleSort;

    /**
     * 是否删除0:未删除。1：已删除
     */
    private Boolean isDelete;

    /**
     * 操作人
     */
    private String actionBy;

}
