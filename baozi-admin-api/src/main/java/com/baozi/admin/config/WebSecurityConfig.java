package com.baozi.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebSecurityConfig
 * @Description Security配置类
 * @Author LiZuoYang
 * @Date 2020/11/19 15:09
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @author lizuoyang
     * @description 静态资源配置
     * @date 15:52 2021/1/19
     * @param web
     * @return void
     **/
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/v2/api-docs",
                        "/api/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/actuator/**",
                        "/druid/**");
    }

    /**
     * @author lizuoyang
     * @description url配置
     * @date 15:52 2021/1/19
     * @param http
     * @return void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //允许根路径url的访问
                .antMatchers("/").permitAll()
                //允许swagger-ui.html访问
                .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin().permitAll();
    }
}
