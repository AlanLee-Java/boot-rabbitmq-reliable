package com.alanlee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot项目启动类
 *
 * @MapperScan("com.alanlee.mapper") 扫描mybatis mapper映射
 */
@SpringBootApplication
@MapperScan("com.alanlee.mapper")
public class BootRabbitmqReliableApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRabbitmqReliableApplication.class, args);
    }

}
