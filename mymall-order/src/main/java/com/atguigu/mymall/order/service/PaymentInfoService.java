package com.atguigu.mymall.order.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:03:44
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

