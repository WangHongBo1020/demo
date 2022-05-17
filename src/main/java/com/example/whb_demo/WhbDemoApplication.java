package com.example.whb_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 */


@EnableConfigurationProperties
@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.whb_demo.mapper")
public class WhbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhbDemoApplication.class, args);
    }

}
