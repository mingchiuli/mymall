package com.atguigu.mymall.ware.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.ware.entity.PurchaseDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-27 11:14:12
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

