package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.SkuInfoEntity;
import com.atguigu.mymall.product.vo.SkuItemVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * sku信息
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    List<SkuInfoEntity> getSkuBySpuId(Long spuId);

    SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException;
}

