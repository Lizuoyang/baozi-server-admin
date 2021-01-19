package com.baozi.admin.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baoz.admin.constants.MenuTypeConstant;
import com.baozi.admin.dto.SysRoleDTO;
import com.baozi.admin.entity.SysMenuDO;
import com.baozi.admin.entity.SysRoleMenuDO;
import com.baozi.admin.manager.SysOperationManager;
import com.baozi.admin.manager.SysRoleManager;
import com.baozi.admin.manager.SysRoleMenuManager;
import com.baozi.admin.query.SysRoleListQuery;
import com.baozi.admin.service.SysMenuService;
import com.baozi.admin.vo.SysRoleVo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName SysRoleApi
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/1/19 19:06
 **/
@Api(tags = "系统角色接口")
@Slf4j
@RestController
@RequestMapping("/api/sys/role")
public class SysRoleApi {
    @Autowired
    private SysRoleManager roleManager;

    @Autowired
    private SysRoleMenuManager roleMenuManager;

    @Autowired
    private SysOperationManager operationManager;

    @Autowired
    private SysMenuService menuService;

    @ApiOperation(
            value = "获取菜单",
            notes = "根据角色获取绑定的菜单和按钮操作权限",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/menus/{roleId}")
    public ApiResponse listMenusByRoleId(@PathVariable String roleId) {
        List<String> menus = roleMenuManager.listMenusByRoleId(roleId);
        if (CollectionUtils.isEmpty(menus)) {
            return ApiResponse.ofSuccess(Lists.newArrayList());
        }
        LambdaQueryWrapper<SysMenuDO> queryQw = new LambdaQueryWrapper<>();
        queryQw.in(SysMenuDO::getId, menus);
        queryQw.eq(SysMenuDO::getMenuType, MenuTypeConstant.MENU);
        List<Map<String, Object>> menuIds = menuService.list(queryQw).stream().map(x -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("menuId", String.valueOf(x.getId()));
            map.put("operations", operationManager.listOperationByRole(roleId, String.valueOf(x.getId())));
            return map;
        }).collect(Collectors.toList());
        return ApiResponse.ofSuccess(menuIds);
    }

    @ApiOperation(
            value = "分配权限",
            notes = "为角色分配菜单权限和按钮权限",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/assign/menu")
    public ApiResponse assignMenus(@RequestBody List<SysRoleMenuDO> list) {
        boolean saveRes = roleMenuManager.saveRoleAndMenuRelationship(list);
        return ApiResponse.ofSuccess(saveRes);
    }

    @ApiOperation(
            value = "查询角色",
            notes = "根据条件分页查询角色列表",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/list/page")
    public ApiResponse listPageRoles(@RequestBody SysRoleListQuery query) {
        PageInfo<SysRoleVo> pageInfo = roleManager.listPageRoles(query);
        return ApiResponse.ofSuccess(pageInfo);
    }

    @ApiOperation(
            value = "查询所有角色",
            notes = "查询所有角色",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/list/all")
    public ApiResponse listAllRoles() {
        List<SysRoleVo> sysRoles = roleManager.listAllRoles();
        return ApiResponse.ofSuccess(sysRoles);
    }

    @ApiOperation(
            value = "新增角色",
            notes = "新增角色",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("add")
    public ApiResponse addRole(@RequestBody SysRoleDTO sysRoleDTO){
        log.info("sysRoleDTO:{}",sysRoleDTO);
        boolean result = roleManager.save(sysRoleDTO);
        return ApiResponse.ofSuccess(result);
    }

    @ApiOperation(
            value = "修改角色",
            notes = "修改角色",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("update")
    public ApiResponse updateRole(@RequestBody SysRoleDTO sysRoleDTO){
        log.info("sysRoleDTO:{}",sysRoleDTO);
        boolean result = roleManager.update(sysRoleDTO);
        return ApiResponse.ofSuccess(result);
    }

    @ApiOperation(
            value = "删除角色",
            notes = "根据ID删除角色",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("delete/{id}")
    public ApiResponse deleteById(@PathVariable(value = "id") String id){
        boolean result = roleManager.deleteById(id);
        return ApiResponse.ofSuccess(result);
    }
}
