package com.atguigu.mymall.ware.service;

import com.atguigu.mymall.common.to.mq.OrderTo;
import com.atguigu.mymall.common.to.mq.StockLockedTo;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.ware.entity.WareSkuEntity;
import com.atguigu.mymall.ware.vo.SkuHasStockVo;
import com.atguigu.mymall.ware.vo.WareSkuLockVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:15:27
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    Boolean orderLockStock(WareSkuLockVo vo);

    void unLockStock(StockLockedTo to);

    void unLockStock(OrderTo orderTo);
}

