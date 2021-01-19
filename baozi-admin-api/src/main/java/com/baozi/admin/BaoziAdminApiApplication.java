package com.baozi.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.baozi.admin.mapper")
public class BaoziAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoziAdminApiApplication.class, args);

        log.info("=======================================");
        log.info("========== Baozi Admin Started =========");
        log.info("=======================================");
    }

}
