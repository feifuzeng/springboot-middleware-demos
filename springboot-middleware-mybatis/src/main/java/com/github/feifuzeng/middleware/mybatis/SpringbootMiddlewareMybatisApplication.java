package com.github.feifuzeng.middleware.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.feifuzeng.middleware.mybatis.domain.mapper")
public class SpringbootMiddlewareMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMiddlewareMybatisApplication.class, args);
    }

}
