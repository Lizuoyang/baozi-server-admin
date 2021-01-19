package com.baozi.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName updateMenuShowDTO
 * @Description 修改菜单是否显示参数类
 * @Author LiZuoYang
 * @Date 2020/12/11 9:19
 **/
@Data
public class UpdateMenuShowDTO implements Serializable {
    private static final long serialVersionUID = 7282775351658623454L;

    /**
     * ID
     */
    private String id;

    /**
     * 子节点ID
     */
    private List<String> childrenIds;

    /**
     * 菜单类型： navigation（导航）， menu（菜单）
     */
    private String menuType;

    /**
     * 是否显示 （0：显示 1： 隐藏）
     */
    private Boolean isDelete;
}
