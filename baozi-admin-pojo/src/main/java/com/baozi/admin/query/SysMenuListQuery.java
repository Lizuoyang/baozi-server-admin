package com.baozi.admin.query;

import lombok.Data;

/**
 * @ClassName SysMenuListQuery
 * @Description 菜单分页查询参数
 * @Author LiZuoYang
 * @Date 2020/12/10 11:01
 **/
@Data
public class SysMenuListQuery extends BaseQuery{
    private static final long serialVersionUID = -9122344345841767614L;

    /**
     * 所属系统ID
     */
    private String systemId;

    /**
     * 菜单类型：导航（navigation） 菜单（menu）
     */
    private String menuType;

    /**
     * 菜单名称
     */
    private String menuName;
}
