package com.alanlee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring boot项目启动类
 *
 * @MapperScan("com.alanlee.mapper") 扫描mybatis mapper映射
 * @EnableTransactionManagement 开启声明式事务
 */
@SpringBootApplication
@MapperScan("com.alanlee.mapper")
@EnableTransactionManagement
public class BootRabbitmqReliableApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRabbitmqReliableApplication.class, args);
    }

}
