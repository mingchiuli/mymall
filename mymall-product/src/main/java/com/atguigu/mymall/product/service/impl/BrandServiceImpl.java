package com.atguigu.mymall.product.service.impl;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.common.utils.Query;
import com.atguigu.mymall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.mymall.product.entity.BrandEntity;
import com.atguigu.mymall.product.service.BrandService;
import com.atguigu.mymall.product.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
* @author mingchiuli
* @description 针对表【pms_brand(品牌)】的数据库操作Service实现
* @createDate 2022-02-21 11:27:19
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandEntity>
    implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //模糊查询
        //1 获取key
        String key = (String)params.get("key");
        //你的检索条件
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("brand_id", key).or().like("name", key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),queryWrapper
        );
        return new PageUtils(page);
    }

    //保证冗余字段的数据一致
    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        this.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            //同步更新其他关联表中的数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            //TODO 更新其他关联
        }
    }

    @Override
    public List<BrandEntity> getBrandsByIds(List<Long> brandIds) {

        return baseMapper.selectList(new QueryWrapper<BrandEntity>().in("brand_id", brandIds));
    }

}




