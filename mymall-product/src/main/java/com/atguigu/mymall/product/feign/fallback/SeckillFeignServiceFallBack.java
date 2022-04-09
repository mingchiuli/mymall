package com.atguigu.mymall.product.feign.fallback;

import com.atguigu.mymall.common.exception.BizCodeEnume;
import com.atguigu.mymall.common.utils.R;
import com.atguigu.mymall.product.feign.SeckillFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 孟享广
 * @date 2021-02-22 4:15 下午
 * @description feign sentinel 熔断保护方法
 */
@Slf4j
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {

    @Override
    public R getSkuSeckillInfo(Long skuId) {
        log.info("熔断方法调用 getSkuSeckillInfo(). ..");
        return R.error(BizCodeEnume.TO_MANY_REQUEST.getCode(), BizCodeEnume.TO_MANY_REQUEST.getMsg());
    }
}
