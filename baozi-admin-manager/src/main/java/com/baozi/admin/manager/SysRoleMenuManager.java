package com.baozi.admin.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baoz.admin.annation.Manager;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.entity.SysRoleMenuDO;
import com.baozi.admin.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysUserRoleManager
 * @Description 角色、菜单关联表manager层
 * @Author LiZuoYang
 * @Date 2020/12/15 19:04
 **/
@Manager
public class SysRoleMenuManager {

    @Autowired
    private SysRoleMenuService roleMenuService;

    /**
     * @author lizuoyang
     * @description 通过角色ID & 菜单ID 查询角色和菜单关联关系
     * @date 11:35 2021/1/12
     * @param roleId
     * @param menuId
     * @return java.util.List<com.purcotton.omni.entity.SysRoleMenu>
     **/
    public SysRoleMenuDO getRoleMenu(String roleId, String menuId) {
        LambdaQueryWrapper<SysRoleMenuDO> queryQw = new LambdaQueryWrapper<>();
        queryQw.eq(SysRoleMenuDO::getRoleId, roleId);
        queryQw.eq(SysRoleMenuDO::getMenuId, menuId);
        SysRoleMenuDO roleMenu = roleMenuService.getOne(queryQw);
        return roleMenu;
    }

    /**
     * @author lizuoyang
     * @description 根据角色获取选中的菜单ID集合
     * @date 11:33 2021/1/12
     * @param roleId
     * @return java.util.List<java.lang.String>
     **/
    public List<String> listMenusByRoleId(String roleId) {
        LambdaQueryWrapper<SysRoleMenuDO> queryQw = new LambdaQueryWrapper<>();
        queryQw.eq(SysRoleMenuDO::getRoleId, roleId);

        List<SysRoleMenuDO> list = roleMenuService.list(queryQw);
        List<String> menuIds = list.stream().map(x -> String.valueOf(x.getMenuId())).collect(Collectors.toList());
        return menuIds;
    }

    /**
     * @author lizuoyang
     * @description 新增角色和菜单的关联
     * @date 19:06 2020/12/15
     * @return boolean
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleAndMenuRelationship(List<SysRoleMenuDO> list) {
        Assert.notNull(list, "参数为空");
        boolean saveRes = Boolean.TRUE;
        LambdaUpdateWrapper<SysRoleMenuDO> updQW = new LambdaUpdateWrapper<>();
        updQW.eq(SysRoleMenuDO::getRoleId, list.get(0).getRoleId());
        // 删除之前的关联
        boolean removeRes = roleMenuService.remove(updQW);
        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(x -> x.setId(SnowflakeIdUtil.buildGenerateId()));
            saveRes = roleMenuService.saveBatch(list);
        }
        return saveRes;
    }
}
