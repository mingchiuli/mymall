package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author mingchiuli
* @description 针对表【pms_brand(品牌)】的数据库操作Service
* @createDate 2022-02-21 11:27:19
*/
public interface BrandService extends IService<BrandEntity> {
    PageUtils queryPage(Map<String, Object> params);

    //保证冗余字段的数据一致
    void updateDetail(BrandEntity brand);

    List<BrandEntity> getBrandsByIds(List<Long> brandIds);

}
