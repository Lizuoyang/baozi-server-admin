package com.baozi.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName SysUserVo
 * @Description 获取用户信息VO
 * @Author LiZuoYang
 * @Date 2020/12/8 10:32
 **/
@Data
public class SysUserVo extends BaseVo {

    private static final long serialVersionUID = 2488077598118066973L;
    /**
     * 主键
     */
    private String id;

    /**
     * 系统用户编号
     */
    private String userCode;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 性别:男、女
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像路径
     */
    private String imageUrl;

    /**
     * 注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;
}
