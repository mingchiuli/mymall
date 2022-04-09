package com.atguigu.mymall.product.feign;

import com.atguigu.mymall.common.to.es.SkuEsModel;
import com.atguigu.mymall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 孟享广
 * @date 2021-01-05 3:08 下午
 * @description
 */

//告诉spring cloud 这个接口是一个远程客户端 调用远程服务
@FeignClient("mymall-search")//这个远程服务
public interface SearchFeignService {

    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
