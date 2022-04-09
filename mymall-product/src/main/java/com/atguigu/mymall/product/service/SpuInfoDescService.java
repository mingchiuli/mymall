package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.SpuInfoDescEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity);
}

