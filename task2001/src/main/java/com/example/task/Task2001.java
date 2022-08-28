package com.example.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author ye
 * createDate 2022/8/17  20:34
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Task2001 {
    public static void main(String[] args) {
        SpringApplication.run(Task2001.class, args);
    }
}
