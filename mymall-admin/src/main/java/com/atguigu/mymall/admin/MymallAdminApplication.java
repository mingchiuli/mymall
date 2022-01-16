package com.atguigu.mymall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MymallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallAdminApplication.class, args);
    }

}
