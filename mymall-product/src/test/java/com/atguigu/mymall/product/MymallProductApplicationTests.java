package com.atguigu.mymall.product;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MymallProductApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(redissonClient);
    }

    @Autowired
    RedissonClient redissonClient;





}
