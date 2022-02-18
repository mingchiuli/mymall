package com.atguigu.mymall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.mymall.product.entity.CategoryEntity;
import com.atguigu.mymall.product.service.CategoryService;
import com.atguigu.mymall.product.mapper.PmsCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author mingchiuli
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
* @createDate 2022-02-16 09:37:18
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, CategoryEntity>
    implements CategoryService {

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2.组装父子的树形结构
        //2.1 找到所有一级分类
        return entities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek(menu-> menu.setChildren(getChildrens(menu, entities)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 检查当前删除的菜单，是否被别的地方引用

        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
//        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> Objects.equals(categoryEntity.getParentCid(), root.getCatId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity, all)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }
}




