package com.atguigu.mymall.member.feign;

import com.atguigu.mymall.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mingchiuli
 * @create 2022-02-05 9:47 PM
 */
@FeignClient("mymall-coupon")
public interface CouponFeignService {

    @GetMapping("/test")
    Result test();

}
