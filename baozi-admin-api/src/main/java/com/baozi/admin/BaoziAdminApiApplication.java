package com.baozi.admin;

import com.baozi.admin.config.ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.baozi.admin.mapper")
@EnableConfigurationProperties({ConfigProperties.class})
public class BaoziAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoziAdminApiApplication.class, args);

        log.info("=======================================");
        log.info("========== Baozi Admin Started =========");
        log.info("=======================================");
    }

}
