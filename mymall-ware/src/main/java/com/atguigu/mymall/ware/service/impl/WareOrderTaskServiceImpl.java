package com.atguigu.mymall.ware.service.impl;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.common.utils.Query;
import com.atguigu.mymall.ware.mapper.WareOrderTaskMapper;
import com.atguigu.mymall.ware.entity.WareOrderTaskEntity;
import com.atguigu.mymall.ware.service.WareOrderTaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskMapper, WareOrderTaskEntity> implements WareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskEntity> page = this.page(
                new Query<WareOrderTaskEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WareOrderTaskEntity getOrderTaskByOrderSn(String orderSn) {

        return this.getOne(new QueryWrapper<WareOrderTaskEntity>().eq("order_sn", orderSn));
    }

}
