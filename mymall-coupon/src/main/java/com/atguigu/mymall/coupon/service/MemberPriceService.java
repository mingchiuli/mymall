package com.atguigu.mymall.coupon.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:38:31
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

