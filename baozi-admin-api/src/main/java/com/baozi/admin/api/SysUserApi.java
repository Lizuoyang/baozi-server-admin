package com.baozi.admin.api;

import com.baozi.admin.dto.SysUserLoginDTO;
import com.baozi.admin.dto.SysUserUpdateDTO;
import com.baozi.admin.manager.SysUserManager;
import com.baozi.admin.query.SysUserListQuery;
import com.baozi.admin.vo.SysUserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统用户接口")
@RestController
@RequestMapping("/api/sys/user")
public class SysUserApi {
    @Autowired
    private SysUserManager userManager;

    @ApiOperation(
            value = "用户登录",
            notes = "验证用户名和密码是否正确，正确的话生成token并返回",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/login")
    public ApiResponse login(@RequestBody SysUserLoginDTO dto) {
        String token = userManager.userLogin(dto);
        return ApiResponse.ofSuccess(token);
    }

    @ApiOperation(
            value = "获取用户信息",
            notes = "根据token从redis中获取登录过的用户信息",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/info/{token}")
    public ApiResponse getUserInfo(@PathVariable String token) {
        SysUserVo userInfo = userManager.getUserInfo(token);
        return ApiResponse.ofSuccess(userInfo);
    }

    @ApiOperation(
            value = "用户登出",
            notes = "用户退出系统并清除redis中token信息",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/login/out/{token}")
    public ApiResponse loginOut(@PathVariable String token) {
        userManager.userLoginOut(token);
        return ApiResponse.ofSuccess();
    }

    @ApiOperation(
            value = "修改用户信息",
            notes = "修改用户信息",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/update")
    public ApiResponse updateUser(@RequestBody SysUserUpdateDTO dto) {
        boolean res = userManager.updateUser(dto);
        return ApiResponse.ofSuccess(res);
    }

    @ApiOperation(
            value = "新增用户",
            notes = "新增一个可使用的账号",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/save")
    public ApiResponse saveUser(@RequestBody SysUserUpdateDTO dto) {
        boolean res = userManager.saveUser(dto);
        return ApiResponse.ofSuccess(res);
    }

    @ApiOperation(
            value = "用户查询",
            notes = "根据条件分页查询用户列表",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/list/page")
    public ApiResponse listPageUsers(@RequestBody SysUserListQuery query) {
        PageInfo<SysUserVo> pageInfo = userManager.listUsers(query);
        return ApiResponse.ofSuccess(pageInfo);
    }

    @ApiOperation(
            value = "用户名校验",
            notes = "判断新的用户名是否已存在",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/check/account/{name}/{value}")
    public ApiResponse checkUserAccount(@PathVariable String name,@PathVariable String value) {
        return ApiResponse.ofSuccess(userManager.checkUserAccount(name, value));
    }
}
