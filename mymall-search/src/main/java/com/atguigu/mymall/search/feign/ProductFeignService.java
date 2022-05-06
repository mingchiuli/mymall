package com.atguigu.mymall.search.feign;

import com.atguigu.mymall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * @author 孟享广
 * @date 2021-01-19 11:04 上午
 * @description
 */

//告诉spring cloud 这个接口是一个远程客户端 调用远程服务
@FeignClient("mymall-product")//这个远程服务
public interface ProductFeignService {

    @GetMapping("product/attr/info/{attrId}")
    R attrInfo(@PathVariable("attrId") Long attrId);

    @GetMapping("product/brand/infos")
    R BrandsInfo(@RequestParam("brandIds") List<Long> brandIds);

}
