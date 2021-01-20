package com.baozi.admin.api;

import com.baozi.admin.dto.SysMenuUpdateDTO;
import com.baozi.admin.dto.UpdateMenuShowDTO;
import com.baozi.admin.manager.SysMenuManager;
import com.baozi.admin.query.SysMenuListQuery;
import com.baozi.admin.vo.SysMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统菜单接口")
@RestController
@RequestMapping("/api/sys/menu")
public class SysMenuApi {
    @Autowired
    private SysMenuManager menuManager;

    @ApiOperation(
            value = "获取菜单",
            notes = "根据角色ID获取角色绑定的菜单和按钮权限",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @GetMapping("/list/{roleId}")
    public ApiResponse listMenusByRoleId(@PathVariable String roleId) {
        List<SysMenuVo> list = menuManager.listMenusByRoleId(roleId);
        return ApiResponse.ofSuccess(list);
    }

    @ApiOperation(
            value = "修改菜单",
            notes = "修改菜单",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/update")
    public ApiResponse updateMenu(@RequestBody SysMenuUpdateDTO dto) {
        boolean updRes = menuManager.updateMenu(dto);
        return ApiResponse.ofSuccess(updRes);
    }

    @ApiOperation(
            value = "修改菜单",
            notes = "修改菜单以及子菜单是否隐藏",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/update/show")
    public ApiResponse updateMenuShow(@RequestBody UpdateMenuShowDTO dto) throws InterruptedException {
        boolean updRes = menuManager.setSysMenuShow(dto);
        return ApiResponse.ofSuccess(updRes);
    }

    @ApiOperation(
            value = "获取导航或菜单",
            notes = "根据条件获取对应的菜单",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/navigation/list")
    public ApiResponse listNavigations(@RequestBody SysMenuListQuery query) {
        List<SysMenuVo> list = menuManager.listNavigationsBySystemId(query);
        return ApiResponse.ofSuccess(list);
    }
}
