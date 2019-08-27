package com.github.feifuzeng.middleware.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.github.feifuzeng.middleware.mp.dao"})
public class SpringbootMiddlewareMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMiddlewareMybatisPlusApplication.class, args);
    }

}
