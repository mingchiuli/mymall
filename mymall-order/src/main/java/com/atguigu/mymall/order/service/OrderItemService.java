package com.atguigu.mymall.order.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:03:44
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

