package com.example.whb_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Administrator
 */


@EnableConfigurationProperties
@EnableScheduling
@MapperScan("com.example.whb_demo.mapper")
//@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@SpringBootApplication
public class WhbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhbDemoApplication.class, args);
    }

}
