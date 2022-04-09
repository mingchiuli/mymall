package com.atguigu.mymall.ware.service.impl;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.common.utils.Query;
import com.atguigu.mymall.ware.mapper.PurchaseDetailMapper;
import com.atguigu.mymall.ware.entity.PurchaseDetailEntity;
import com.atguigu.mymall.ware.service.PurchaseDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();

        String key = (String)params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj)->{
                obj.eq("sku_id", key).or().eq("purchase_id", key);
            });
        }

        String status = (String)params.get("status");
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        String wareId = (String)params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            wrapper.eq("ware_id", wareId);
        }

        Page<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params), wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<PurchaseDetailEntity> listDetailByPurchaseId(Long id) {
        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<PurchaseDetailEntity>().eq("purchase_id", id);
        List<PurchaseDetailEntity> list = this.list(wrapper);
        return list;
    }
}
