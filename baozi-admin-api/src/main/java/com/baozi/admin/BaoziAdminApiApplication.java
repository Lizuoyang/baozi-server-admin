package com.baozi.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.baozi.admin.mapper")
public class BaoziAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaoziAdminApiApplication.class, args);

        log.info("=======================================");
        log.info("========== Baozi Admin Started =========");
        log.info("=======================================");
    }

}
