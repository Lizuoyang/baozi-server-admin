package com.baozi.admin.api;

import com.baozi.admin.config.OperationLog;
import com.baozi.admin.dto.SystemUpdateDTO;
import com.baozi.admin.manager.SysSystemManager;
import com.baozi.admin.query.SystemListQuery;
import com.baozi.admin.vo.SysSystemVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统管理接口")
@RestController
@RequestMapping("/sys/tem")
public class SystemApi {
    @Autowired
    private SysSystemManager systemManager;

    @OperationLog("修改系统")
    @ApiOperation(
            value = "修改系统",
            notes = "修改系统",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/update")
    public ApiResponse updateSystem(@RequestBody SystemUpdateDTO dto) {
        return ApiResponse.ofSuccess(systemManager.saveOrupdate(dto));
    }

    @OperationLog("查询系统列表")
    @ApiOperation(
            value = "查询系统列表",
            notes = "根据条件分页查询系统列表",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @PostMapping("/list/page")
    public ApiResponse listPageSystems(@RequestBody SystemListQuery query) {
        PageInfo<SysSystemVo> pageInfo = systemManager.listPageSystems(query);
        return ApiResponse.ofSuccess(pageInfo);
    }

    @OperationLog("查询全部系统")
    @ApiOperation(
            value = "查询全部系统",
            notes = "查询所有的系统列表",
            httpMethod = "POST",
            response = ApiResponse.class
    )
    @GetMapping("/list")
    public ApiResponse listSystems() {
        List<SysSystemVo> sysSystemVos = systemManager.listSystemsAll();
        return ApiResponse.ofSuccess(sysSystemVos);
    }
}
