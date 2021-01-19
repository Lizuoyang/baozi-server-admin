package com.baozi.admin.vo;

import com.baozi.admin.entity.SysOperationDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysMenuVo
 * @Description 菜单列表vo
 * @Author LiZuoYang
 * @Date 2020/12/10 11:05
 **/
@Data
public class SysMenuVo implements Serializable {

    private static final long serialVersionUID = 4565760378414896456L;
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
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 子菜单
     */
    private List<SysMenuVo> children;

    /**
     * 菜单选中的按钮操作权限code
     */
    private List<String> operationCodes;

    /**
     * 菜单可选择的操作按钮权限列表
     */
    private List<SysOperationDO> operations;
}
