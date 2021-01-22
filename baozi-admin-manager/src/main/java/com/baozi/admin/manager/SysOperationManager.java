package com.baozi.admin.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baoz.admin.annation.Manager;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.entity.SysMenuOperationDO;
import com.baozi.admin.entity.SysOperationDO;
import com.baozi.admin.entity.SysRoleMenuDO;
import com.baozi.admin.service.SysMenuOperationService;
import com.baozi.admin.service.SysOperationService;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysOperationManager
 * @Description 操作按钮manager
 * @Author LiZuoYang
 * @Date 2021/1/11 16:07
 **/
@Manager
public class SysOperationManager {

    @Autowired
    private SysMenuOperationService menuOperationService;

    @Autowired
    private SysOperationService operationService;

    @Autowired
    private SysRoleMenuManager roleMenuManager;

    /**
     * @author lizuoyang
     * @description 根据角色ID和菜单ID获取该角色拥有的按钮操作权限
     * @date 16:12 2021/1/11
     * @param menuId
     * @return java.util.List<java.lang.String>
     **/
    public List<String> listOperationCodesByMenuId(String roleId, String menuId) {
        List<SysOperationDO> sysOperations = this.listOperationByRole(roleId, menuId);
        if (CollectionUtils.isEmpty(sysOperations)) {
            return Lists.newArrayList();
        }
        List<String> list = sysOperations.stream().map(x -> x.getOperationCode()).collect(Collectors.toList());
        return list;
    }

    /**
     * @author lizuoyang
     * @description 根据菜单ID获取操作权限ID列表
     * @date 16:12 2021/1/11
     * @param menuId
     * @return java.util.List<java.lang.String>
     **/
    public List<SysOperationDO> listOperationsByMenuId(String menuId) {
        LambdaQueryWrapper<SysMenuOperationDO> lambdaQw = new LambdaQueryWrapper<>();
        lambdaQw.eq(SysMenuOperationDO::getMenuId, menuId);
        List<SysMenuOperationDO> list = menuOperationService.list(lambdaQw);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<String> result = list.stream().map(x -> String.valueOf(x.getOperationId())).collect(Collectors.toList());
        List<SysOperationDO> operations = operationService.listByIds(result);
        return CollectionUtils.isEmpty(operations) ? Lists.newArrayList() : operations;
    }

    /**
     * @author lizuoyang
     * @description 批量绑定菜单操作按钮权限
     * @date 16:29 2021/1/11
     * @param menuId
     * @param operationIds
     * @return void
     **/
    public void saveOperationWithMenu(String menuId, List<String> operationIds) {
        // 删除之前的操作按钮权限
        LambdaQueryWrapper<SysMenuOperationDO> delQw = new LambdaQueryWrapper<>();
        delQw.eq(SysMenuOperationDO::getMenuId, menuId);
        menuOperationService.remove(delQw);

        List<SysMenuOperationDO> saveEntitys = operationIds.stream().map(m -> {
            // 设置新的操作按钮权限
            SysMenuOperationDO saveEntity = SysMenuOperationDO.builder()
                    .menuId(menuId)
                    .operationId(m)
                    .id(SnowflakeIdUtil.buildGenerateId())
                    .createdTime(new Date())
                    .build();

            return saveEntity;
        }).collect(Collectors.toList());

        menuOperationService.saveBatch(saveEntitys);

    }

    /**
     * @author lizuoyang
     * @description 通过code获取操作按钮详情
     * @date 11:31 2021/1/12
     * @param code
     * @return com.purcotton.omni.entity.SysOperation
     **/
    public SysOperationDO getOperationByCode(String code) {
        LambdaQueryWrapper<SysOperationDO> qw = new LambdaQueryWrapper<>();
        qw.eq(SysOperationDO::getOperationCode, code);
        SysOperationDO op = operationService.getOne(qw);
        return op;
    }

    /**
     * @author lizuoyang
     * @description 获取角色下菜单选择的操作按钮权限
     * @date 13:46 2021/1/12
     * @param roleId
     * @param menuId
     * @return java.util.List<com.purcotton.omni.entity.SysOperation>
     **/
    public List<SysOperationDO> listOperationByRole(String roleId, String menuId) {
        SysRoleMenuDO sysRoleMenu = roleMenuManager.getRoleMenu(roleId, menuId);
        String operationStr = sysRoleMenu.getOperationList();
        if (StringUtil.isEmpty(operationStr)) {
            return Lists.newArrayList();
        }
        String[] operationSplit = operationStr.split(",");
        LambdaQueryWrapper<SysOperationDO> qw = new LambdaQueryWrapper<>();
        qw.in(SysOperationDO::getOperationCode, operationSplit);
        List<SysOperationDO> list = operationService.list(qw);
        return list;

    }

}
