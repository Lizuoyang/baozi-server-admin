package com.baozi.admin.manager;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baoz.admin.annation.Manager;
import com.baoz.admin.constants.IdPrefixConstant;
import com.baoz.admin.constants.MenuTypeConstant;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.convert.SysMenuConvert;
import com.baozi.admin.dto.SysMenuUpdateDTO;
import com.baozi.admin.dto.UpdateMenuShowDTO;
import com.baozi.admin.entity.SysMenuDO;
import com.baozi.admin.query.SysMenuListQuery;
import com.baozi.admin.service.SysMenuService;
import com.baozi.admin.vo.SysMenuVo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysMenuManager
 * @Description 系统菜单逻辑处理
 * @Author LiZuoYang
 * @Date 2021/1/19 18:37
 **/
@Manager
public class SysMenuManager {
    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysRoleMenuManager roleMenuManager;

    @Autowired
    private SysOperationManager operationManager;

    public List<SysMenuVo> listMenusByRoleId(String roleId) {
        List<String> menuIds = roleMenuManager.listMenusByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<SysMenuDO> queryQW = new LambdaQueryWrapper<>();
        queryQW.in(SysMenuDO::getId, menuIds);
        queryQW.orderByAsc(SysMenuDO::getMenuSort);
        List<SysMenuDO> sysMenus = menuService.list(queryQW);
        List<SysMenuVo> sysMenuVos = SysMenuConvert.INSTANCE.dos2vos(sysMenus);
        List<SysMenuVo> menuTreeList = getMenuTreeList(sysMenuVos, "0",roleId);
        return menuTreeList;
    }


    /**
     * @author lizuoyang
     * @description 保存 | 修改 菜单
     * @date 11:31 2020/12/15
     * @param dto
     * @return boolean
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenu(SysMenuUpdateDTO dto) {
        boolean updResult = Boolean.FALSE;
        // 校验参数
        checkUpdateMenuParam(dto);

        SysMenuDO updMenu = SysMenuConvert.INSTANCE.dto2do(dto);
        if (StringUtil.isNotEmpty(dto.getId())) {
            // 修改菜单
            updMenu.setUpdatedTime(LocalDateTime.now());
            updMenu.setId(dto.getId());
            updResult = menuService.updateById(updMenu);

        } else {
            updMenu.setId(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_MENU));
            // 新增菜单
            updMenu.setCreatedTime(LocalDateTime.now());
            updResult = menuService.save(updMenu);
        }

        //新增操作权限
        operationManager.saveOperationWithMenu(updMenu.getId(), dto.getOperationIds());

        return updResult;
    }

    /**
     * @author lizuoyang
     * @description 保存 | 修改 菜单参数校验
     * @date 14:52 2020/12/15
     * @param dto
     * @return void
     **/
    private void checkUpdateMenuParam(SysMenuUpdateDTO dto) {
        Assert.notNull(dto.getMenuName(), "menuName 不能为空");
        Assert.notNull(dto.getMenuType(), "menuType 不能为空");
        Assert.notNull(dto.getSystemId(), "systemId 不能为空");
        Assert.notNull(dto.getParentId(), "parentId 不能为空");
    }


    /**
     * @author lizuoyang
     * @description 设置 导航 | 菜单 是否显示
     * @date 9:42 2020/12/11
     * @param dto
     * @return boolean
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean setSysMenuShow(UpdateMenuShowDTO dto) {
        boolean result = Boolean.FALSE;
        // 修改菜单的is_delete字段值
        result = updateSysmenuBatch(Arrays.asList(dto.getId()), dto.getIsDelete());
        if (ObjectUtil.equal(MenuTypeConstant.NAVIGATION, dto.getMenuType()) && !CollectionUtils.isEmpty(dto.getChildrenIds())) {
            // 如果是导航节点需要把子菜单的is_delete全部修改
            updateSysmenuBatch(dto.getChildrenIds(), dto.getIsDelete());
        }
        return result;
    }

    /**
     * @author lizuoyang
     * @description 批量修改菜单is_delete
     * @date 9:46 2020/12/11
     * @param ids
     * @param isDelete
     * @return boolean
     **/
    private boolean updateSysmenuBatch(List<String> ids, Boolean isDelete) {
        List<SysMenuDO> list = ids.stream().map(x -> {
            SysMenuDO menu = new SysMenuDO();
            menu.setId(x);
            menu.setIsDelete(isDelete);
            return menu;
        }).collect(Collectors.toList());

        boolean batchRes = menuService.updateBatchById(list);
        return batchRes;
    }

    /**
     * @param query
     * @return java.util.List<com.purcotton.omni.entity.SysMenu>
     * @author lizuoyang
     * @description 根据系统编码查询所有导航
     * @date 14:44 2020/12/10
     **/
    public List<SysMenuVo> listNavigationsBySystemId(SysMenuListQuery query) {
        LambdaQueryWrapper<SysMenuDO> qw = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEmpty(query.getSystemId())) {
            qw.eq(SysMenuDO::getSystemId, query.getSystemId());
        }

        if (StringUtil.isNotEmpty(query.getMenuType())) {
            qw.eq(SysMenuDO::getMenuType, query.getMenuType());
        }

        if (StringUtil.isNotEmpty(query.getMenuName())) {
            qw.like(SysMenuDO::getMenuName, query.getMenuName());
        }

        qw.orderByDesc(SysMenuDO::getSystemId);
        qw.orderByAsc(SysMenuDO::getMenuSort);
        // 查询该系统下所有菜单
        List<SysMenuDO> list = menuService.list(qw);
        List<SysMenuVo> sysMenuVos = SysMenuConvert.INSTANCE.dos2vos(list);

        List<SysMenuVo> menuTreeList = getMenuTreeList(sysMenuVos, "0", null);
        return StringUtil.isNotEmpty(query.getMenuName()) ? sysMenuVos : menuTreeList;

    }

    /**
     * @param menuVoList
     * @param pid
     * @return java.util.List<com.purcotton.omni.vo.SysMenuVo>
     * @author lizuoyang
     * @description 将菜单转换成树结构
     * @date 16:38 2020/12/10
     **/
    private List<SysMenuVo> getMenuTreeList(List<SysMenuVo> menuVoList, String pid, String roleId) {
        List<SysMenuVo> childrenMenus = Lists.newArrayList();
        menuVoList.stream()
                .filter(all -> ObjectUtil.equal(pid, all.getParentId()))
                .collect(Collectors.toList())
                .forEach(item -> {
                    item.setOperations(operationManager.listOperationsByMenuId(item.getId()));
                    if (StringUtil.isNotEmpty(roleId)) {
                        item.setOperationCodes(operationManager.listOperationCodesByMenuId(roleId, item.getId()));
                    }
                    if (ObjectUtil.equal(item.getMenuType(), MenuTypeConstant.NAVIGATION)) {
                        item.setChildren(getMenuTreeList(menuVoList, item.getId(), roleId));
                    }
                    childrenMenus.add(item);
                });

        return childrenMenus;
    }
}
