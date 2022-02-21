package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.BrandEntity;
import com.atguigu.mymall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author mingchiuli
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service
* @createDate 2022-02-18 18:55:07
*/
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //保存详细细节
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrand(Long brandId, String name);

    //级联更新
    void updateCategory(Long catId, String name);

    List<BrandEntity> getBrandsBycatId(Long catId);

}
