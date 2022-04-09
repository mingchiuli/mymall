package com.atguigu.mymall.order.mapper;

import com.atguigu.mymall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:03:44
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

    void updateOrderStatus(@Param("outTradeNo") String outTradeNo, @Param("code") Integer code);
}
