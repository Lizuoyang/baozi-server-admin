package com.baozi.admin.query;

import lombok.Data;

/**
 * @ClassName SysRoleListQuery
 * @Description 角色列表查询条件
 * @Author LiZuoYang
 * @Date 2020/12/7 16:58
 **/
@Data
public class SysRoleListQuery extends BaseQuery {

    private static final long serialVersionUID = 2711641004534989625L;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 是否删除： 0-未删除 1-删除
     */
    private Boolean isDelete;
}
