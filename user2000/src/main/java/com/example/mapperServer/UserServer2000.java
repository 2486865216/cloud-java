package com.example.mapperServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author ye
 * createDate 2022/8/10  20:38
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class UserServer2000 {
    public static void main(String[] args) {
        SpringApplication.run(UserServer2000.class, args);
    }
}
