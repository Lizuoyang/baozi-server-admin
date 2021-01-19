package com.baozi.admin.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName SysUserListQuery
 * @Description 查询用户列表参数
 * @Author LiZuoYang
 * @Date 2020/12/8 17:48
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserListQuery extends BaseQuery{
    private static final long serialVersionUID = -7197332531791906138L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 是否删除 0-正常 1-删除
     */
    private Integer isDelete;
}
