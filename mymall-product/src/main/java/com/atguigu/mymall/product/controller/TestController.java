package com.atguigu.mymall.product.controller;

import com.atguigu.mymall.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mingchiuli
 * @create 2022-02-05 9:09 PM
 */
public class TestController {
    
    @GetMapping("/test")
    public Result test() {
        return Result.succ("成功测试");
    }
}
