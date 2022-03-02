package com.atguigu.mymall.product.vo;

import com.atguigu.mymall.product.entity.SkuImagesEntity;
import com.atguigu.mymall.product.entity.SkuInfoEntity;
import com.atguigu.mymall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * @author 孟享广
 * @date 2021-01-26 12:01 下午
 * @description
 */

@Data
public class SkuItemVo {

    //1 sku基本信息获取 pms_sku_info
    SkuInfoEntity info;

    //是否有货
    boolean hasStock = true;

    //2 sku图片信息 pms_sku_images
    List<SkuImagesEntity> images;

    //3 spu的销售属性组合
    List<com.atguigu.mymall.product.vo.SkuItemSaleAttrVo> saleAttr;

    //4 spu的介绍
    SpuInfoDescEntity desc;

    //5 spu的规格参数信息
    private List<com.atguigu.mymall.product.vo.SpuItemAttrGroupVo> groupAttrs;

    //6 当前商品秒杀的优惠信息
    private com.atguigu.mymall.product.vo.SeckillSkuInfoVo seckillSkuInfoVo;

}
