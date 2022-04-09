package com.atguigu.mymall.order.vo;

import com.atguigu.mymall.order.entity.OrderEntity;
import com.atguigu.mymall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 孟享广
 * @date 2021-02-09 2:50 下午
 * @description
 */
@Data
public class OrderCreatTo {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    private BigDecimal payPrice;

    private BigDecimal fare;
}
