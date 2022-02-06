package com.atguigu.mymall.coupon.controller;

import com.atguigu.mymall.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mingchiuli
 * @create 2022-02-05 10:05 PM
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${coupon.user.name}")
    private String name;

    @Value("${coupon.user.age}")
    private Integer age;

    @GetMapping("/test")
    public Result test() {
        return Result.succ("这是coupon" + name + age);
    }
}
