package com.atguigu.mymall.order.service;

import com.atguigu.mymall.common.to.mq.SeckillOrderTo;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.order.entity.OrderEntity;
import com.atguigu.mymall.order.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:03:44
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    OrderEntity getOrderByOrderSn(String orderSn);

    void closeOrder(OrderEntity entity);

    PayVo getPayOrder(String orderSn);

    PageUtils queryPageWithItem(Map<String, Object> params);

    String handlePayResult(PayAsyncVo payAsyncVo);

    void creatSeckillOrder(SeckillOrderTo seckillOrderTo);
}

