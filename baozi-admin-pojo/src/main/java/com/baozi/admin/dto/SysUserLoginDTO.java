package com.baozi.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysUserLoginDto
 * @Description 系统登录参数
 * @Author LiZuoYang
 * @Date 2020/12/8 8:51
 **/
@Data
public class SysUserLoginDTO implements Serializable {
    private static final long serialVersionUID = -8656969064439881771L;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;
}
