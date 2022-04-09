package com.atguigu.mymall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atguigu.mymall.product.feign")
@MapperScan(basePackages = "com.atguigu.mymall.product.mapper")
public class MymallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallProductApplication.class, args);
    }

}
