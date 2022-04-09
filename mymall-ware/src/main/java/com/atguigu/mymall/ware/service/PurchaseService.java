package com.atguigu.mymall.ware.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.ware.entity.PurchaseEntity;
import com.atguigu.mymall.ware.vo.MergeVo;
import com.atguigu.mymall.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-27 11:14:12
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceive(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseDoneVo purchaseDoneVo);
}

