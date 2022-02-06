package com.atguigu.mymall.member.controller;

import com.atguigu.mymall.common.utils.Result;
import com.atguigu.mymall.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mingchiuli
 * @create 2022-02-05 9:45 PM
 */
@RestController
public class TestController {

    @Autowired
    CouponFeignService couponFeignService;

    @GetMapping("/test0")
    public Result test() {

        Result test = couponFeignService.test();

        return Result.succ(test);
    }
}
