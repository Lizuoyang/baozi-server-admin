package com.baozi.admin.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baoz.admin.constants.IdPrefixConstant;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.convert.SysOperationConvert;
import com.baozi.admin.dto.UpdateOperationDTO;
import com.baozi.admin.entity.SysOperationDO;
import com.baozi.admin.manager.SysOperationManager;
import com.baozi.admin.service.SysOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "系统操作接口")
@RestController
@RequestMapping("/sys/operation")
public class SysOperationApi {
    @Autowired
    private SysOperationService operationService;

    @Autowired
    private SysOperationManager operationManager;

    @ApiOperation(
            value = "获取菜单按钮",
            notes = "根据条件查询菜单按钮",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/list/all")
    public ApiResponse listAllOperation(@RequestParam(value = "operationName", required = false, defaultValue = "") String operationName) {
        LambdaQueryWrapper<SysOperationDO> lambdaQw = new LambdaQueryWrapper<>();
        lambdaQw.like(SysOperationDO::getOperationName, operationName);
        lambdaQw.orderByAsc(SysOperationDO::getOperationSort);
        List<SysOperationDO> list = operationService.list(lambdaQw);
        return ApiResponse.ofSuccess(list);

    }

    @ApiOperation(
            value = "新增菜单按钮",
            notes = "新增菜单按钮",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/add")
    public ApiResponse addOperation(@RequestBody UpdateOperationDTO params) {
        SysOperationDO operation = SysOperationConvert.INSTANCE.dto2do(params);
        operation.setId(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_OPERATION));
        operation.setCreatedTime(LocalDateTime.now());
        boolean res = operationService.save(operation);
        return ApiResponse.ofSuccess(res);
    }

    @ApiOperation(
            value = "修改菜单按钮",
            notes = "修改菜单按钮",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/update")
    public ApiResponse updateOperation(@RequestBody UpdateOperationDTO params) {
        SysOperationDO operation = SysOperationConvert.INSTANCE.dto2do(params);
        operation.setUpdatedTime(LocalDateTime.now());
        boolean res = operationService.updateById(operation);
        return ApiResponse.ofSuccess(res);
    }

    @ApiOperation(
            value = "删除菜单按钮",
            notes = "删除菜单按钮",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @GetMapping("/remove/{id}")
    public ApiResponse removeOperation(@PathVariable String id) {
        boolean result = operationService.removeById(id);
        return ApiResponse.ofSuccess(result);
    }

    @ApiOperation(
            value = "获取菜单按钮",
            notes = "根据菜单编码获取菜单按钮",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/get/code/{code}")
    public ApiResponse getOperationByCode(@PathVariable String code) {
        SysOperationDO operationByCode = operationManager.getOperationByCode(code);
        return ApiResponse.ofSuccess(operationByCode);
    }
}
