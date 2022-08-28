package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * author ye
 * createDate 2022/8/11  17:29
 */
@SpringBootApplication
@EnableFeignClients
public class ApiMain4002 {
    public static void main(String[] args) {
        SpringApplication.run(ApiMain4002.class, args);
    }
}
