package com.baozi.admin.manager;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baoz.admin.annation.Manager;
import com.baoz.admin.constants.IdPrefixConstant;
import com.baoz.admin.enums.common.GlobalEnums;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.convert.SysRoleCovert;
import com.baozi.admin.dto.SysRoleDTO;
import com.baozi.admin.entity.SysRoleDO;
import com.baozi.admin.query.SysRoleListQuery;
import com.baozi.admin.service.SysRoleService;
import com.baozi.admin.vo.SysRoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysRoleManager
 * @Description 角色管理 Manager层
 * @Author LiZuoYang
 * @Date 2020/12/7 17:01
 **/
@Manager
public class SysRoleManager {

    @Autowired
    private SysRoleService roleService;

    public List<SysRoleVo> listAllRoles() {
        LambdaQueryWrapper<SysRoleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRoleDO::getIsDelete, GlobalEnums.NORMAL);
        List<SysRoleDO> list = roleService.list(lambdaQueryWrapper);
        List<SysRoleVo> sysRoleVos = SysRoleCovert.INSTANCE.dos2vos(list);
        return sysRoleVos;
    }

    public PageInfo<SysRoleVo> listPageRoles(SysRoleListQuery query) {
        LambdaQueryWrapper<SysRoleDO> qw = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEmpty(query.getRoleName())) {
            qw.eq(SysRoleDO::getRoleName, query.getRoleName());
        }

        if (StringUtil.isNotEmpty(query.getRoleCode())) {
            qw.like(SysRoleDO::getRoleCode, query.getRoleCode());
        }

        if (ObjectUtil.isNotNull(query.getIsDelete())) {
            qw.eq(SysRoleDO::getIsDelete, query.getIsDelete());
        }
        qw.orderByAsc(SysRoleDO::getIsDelete);
        qw.orderByDesc(SysRoleDO::getCreatedTime);

        PageHelper.startPage(query.getPage(), query.getLimit());
        List<SysRoleDO> list = roleService.list(qw);
        List<SysRoleVo> sysRoleVos = SysRoleCovert.INSTANCE.dos2vos(list);
        PageInfo<SysRoleVo> pageInfo = new PageInfo<>(sysRoleVos);
        pageInfo.setList(sysRoleVos);
        return pageInfo;
    }

    public boolean save(SysRoleDTO sysRoleDTO) {
        SysRoleDO sysRole = SysRoleCovert.INSTANCE.dto2do(sysRoleDTO);
        sysRole.setId(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_ROLE));
        sysRole.setCreatedTime(new Date());
        boolean result = roleService.save(sysRole);
        return result;
    }

    public boolean update(SysRoleDTO sysRoleDTO) {
        SysRoleDO sysRole = SysRoleCovert.INSTANCE.dto2do(sysRoleDTO);
        sysRole.setUpdatedTime(new Date());
        boolean res = roleService.updateById(sysRole);
        return res;
    }


    public boolean deleteById(String id) {
        boolean result = roleService.removeById(id);
        return result;
    }
}
