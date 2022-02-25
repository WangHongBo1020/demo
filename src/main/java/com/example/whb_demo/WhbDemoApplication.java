package com.example.whb_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 */

@EnableTransactionManagement
@SpringBootApplication
public class WhbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhbDemoApplication.class, args);
    }

}
