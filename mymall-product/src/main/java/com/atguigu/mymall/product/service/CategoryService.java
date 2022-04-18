package com.atguigu.mymall.product.service;

import com.atguigu.mymall.product.entity.CategoryEntity;
import com.atguigu.mymall.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author mingchiuli
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service
* @createDate 2022-02-16 09:37:18
*/
public interface CategoryService extends IService<CategoryEntity> {

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);


    void updateCascade(CategoryEntity category);

    Long[] findCatelogPath(Long catelogId);

    List<CategoryEntity> getLevel1Catrgorys();

    Map<String, List<Catelog2Vo>> getCatalogJson();
}
