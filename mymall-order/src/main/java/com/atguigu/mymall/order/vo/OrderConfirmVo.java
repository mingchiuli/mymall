package com.atguigu.mymall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 孟享广
 * @date 2021-02-07 1:49 下午
 * @description 订单确认页需要的数据
 */
@Data
public class OrderConfirmVo {

    //收货地址列表
    List<com.atguigu.mymall.order.vo.MemberAddressVo> address;

    //所有选中的购物项
    List<com.atguigu.mymall.order.vo.OrderItemVo> items;

    //发票....

    //优惠券 积分
    private Integer integration;

    //订单的防重令牌
    String orderToken;

    Map<Long, Boolean> stocks;

    //总件数
    public Integer getCount() {
        Integer sum = 0;
        if (items != null) {
            for (com.atguigu.mymall.order.vo.OrderItemVo item : items) {
                sum += item.getCount();
            }
        }
        return sum;
    }

    //订单总额 需要计算
    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if (items != null) {
            for (com.atguigu.mymall.order.vo.OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum = sum.add(multiply);
            }
        }
        return sum;
    }

//    private BigDecimal payPrice;
    //应付价格 需要计算
    public BigDecimal getPayPrice() {

        return getTotal();
    }
}
