package com.atguigu.mymall.member.feign;

import com.atguigu.mymall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mingchiuli
 * @create 2022-02-05 9:47 PM
 */
@FeignClient("mymall-coupon")//这个远程服务
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    R memberCoupons();
}
