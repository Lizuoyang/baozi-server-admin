package com.baozi.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysUserUpdateDTO
 * @Description 系统用户 修改 参数类
 * @Author LiZuoYang
 * @Date 2020/12/9 10:09
 **/
@Data
public class SysUserUpdateDTO implements Serializable {
    private static final long serialVersionUID = -2135607418719196655L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别:男0、女1
     */
    private Boolean sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否删除0:未删除。1：已删除
     */
    private Boolean isDelete;

    /**
     * 操作人
     */
    private String actionBy;

    /**
     * 选择的角色
     */
    private String roleId;
}
