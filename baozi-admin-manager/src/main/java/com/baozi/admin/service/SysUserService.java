package com.baozi.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baozi.admin.entity.SysUserDO;
import com.baozi.admin.query.SysUserListQuery;
import com.baozi.admin.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 * 系统用户表  服务类
 * </p>
 *
 * @author baozi-mybatis-plus-general
 * @since 2020-12-23
 */
public interface SysUserService extends IService<SysUserDO> {
    /**
     * @author lizuoyang
     * @description 多条件查询用户列表
     * @date 17:42 2021/1/19
     * @param query
     * @return java.util.List<com.baozi.admin.vo.SysUserVo>
     **/
    List<SysUserVo> listUsersWithRoleInfo(SysUserListQuery query);

    /**
     * @author lizuoyang
     * @description 根据用户ID查询用户详情
     * @date 17:42 2021/1/19
     * @param userId
     * @return com.baozi.admin.vo.SysUserVo
     **/
    SysUserVo getUserWithRoleInfo(String userId);
}
