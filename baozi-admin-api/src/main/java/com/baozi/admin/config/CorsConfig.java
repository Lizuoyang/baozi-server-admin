package com.baozi.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName CorsConfig
 * @Description 允许跨域配置
 * @Author LiZuoYang
 * @Date 2020/12/7 20:04
 **/
@Configuration
@AutoConfigureAfter(value = {ConfigProperties.class})
@EnableAsync
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ConfigProperties properties;

    /**
     * 登录拦截器注册
     * @return 登录拦截器
     */
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor(properties.getExcludeUrl());
    }


    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").pathMatcher(new AntPathMatcher());
        super.addInterceptors(registry);
    }
}
