package com.example.eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * author ye
 * createDate 2022/8/10  20:32
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerMain1999 {
    public static void main(String[] args) {
        SpringApplication.run(ServerMain1999.class, args);
    }
}
