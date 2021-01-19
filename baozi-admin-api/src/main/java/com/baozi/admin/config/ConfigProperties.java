package com.baozi.admin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName ConfigProperties
 * @Description 配置文件属性类
 * @Author LiZuoYang
 * @Date 2020/12/8 15:26
 **/
@ConfigurationProperties(prefix = "baozi")
@Getter
@Setter
public class ConfigProperties {

    private List<String> excludeUrl;
}
