package com.baozi.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysMenuSaveDTO
 * @Description 菜单 保存 | 修改 参数接收类
 * @Author LiZuoYang
 * @Date 2020/12/15 11:25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuUpdateDTO implements Serializable {
    private static final long serialVersionUID = 229078699140479784L;
    /**
     * 主键
     */
    private String id;

    /**
     * 系统编码，所有都要存
     */
    private String systemId;

    /**
     * 父主键：系统下的一级菜单存0
     */
    private String parentId;

    /**
     * 菜单类型：菜单(menu)、导航(navigation)
     */
    private String menuType;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单链接
     */
    private String menuUrl;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0:未删除。1：已删除
     */
    private Boolean isDelete;

    /**
     * 操作人
     */
    private String actionBy;

    /**
     * 操作按钮权限
     */
    private List<String> operationIds;

}
