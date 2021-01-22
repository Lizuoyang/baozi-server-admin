package com.baozi.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户单独菜单中间表 用户单独菜单中间表，里面用字段存操作功能集合,例子：create,import。预留单独给用户分配菜单、操作的功能
 * </p>
 *
 * @author baozi-mybatis-plus-general
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sys_user_menu")
public class SysUserMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 菜单主键
     */
    private String menuId;

    /**
     * 操作权限集合
     */
    private String operationList;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;


}
