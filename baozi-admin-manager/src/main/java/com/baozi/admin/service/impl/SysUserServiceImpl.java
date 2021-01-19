package com.baozi.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baozi.admin.entity.SysUserDO;
import com.baozi.admin.mapper.SysUserMapper;
import com.baozi.admin.query.SysUserListQuery;
import com.baozi.admin.service.SysUserService;
import com.baozi.admin.vo.SysUserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户表  服务实现类
 * </p>
 *
 * @author baozi-mybatis-plus-general
 * @since 2020-12-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService {
    @Override
    public List<SysUserVo> listUsersWithRoleInfo(SysUserListQuery query) {
        return this.baseMapper.selectListWithRoleInfo(query);
    }

    @Override
    public SysUserVo getUserWithRoleInfo(String userId) {
        return this.baseMapper.selectOneWithRoleInfo(userId);
    }

}
