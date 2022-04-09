package com.atguigu.mymall.coupon.service;

import com.atguigu.mymall.common.to.SkuReductionTo;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.coupon.entity.SkuFullReductionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:38:31
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo skuReductionTo);
}

