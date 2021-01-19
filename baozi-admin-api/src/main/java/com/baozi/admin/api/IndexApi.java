package com.baozi.admin.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexApi
 * @Description 测试API接口
 * @Author LiZuoYang
 * @Date 2021/1/19 14:54
 **/
@Api(tags = "测试接口")
@RestController
public class IndexApi {
    @ApiOperation(
            value = "测试",
            notes = "输出Hello Springboot",
            httpMethod = "GET",
            response = ApiResponse.class
    )
    @GetMapping("/hello")
    public ApiResponse hello() {
        return ApiResponse.ofMessage("Hello Spring boot !!!");
    }
}
