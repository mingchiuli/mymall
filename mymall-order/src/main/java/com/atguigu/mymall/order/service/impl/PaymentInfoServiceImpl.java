package com.atguigu.mymall.order.service.impl;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.common.utils.Query;
import com.atguigu.mymall.order.mapper.PaymentInfoMapper;
import com.atguigu.mymall.order.entity.PaymentInfoEntity;
import com.atguigu.mymall.order.service.PaymentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfoEntity> implements PaymentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentInfoEntity> page = this.page(
                new Query<PaymentInfoEntity>().getPage(params),
                new QueryWrapper<PaymentInfoEntity>()
        );

        return new PageUtils(page);
    }

}
