package com.atguigu.mymall.coupon.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.coupon.entity.SkuLadderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:38:31
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

