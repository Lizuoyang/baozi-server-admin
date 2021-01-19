package com.baozi.admin.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexApi
 * @Description 测试API接口
 * @Author LiZuoYang
 * @Date 2021/1/19 14:54
 **/
@RestController
public class IndexApi {
    @GetMapping("/hello")
    public ApiResponse hello() {
        return ApiResponse.ofMessage("Hello Spring boot !!!");
    }
}
