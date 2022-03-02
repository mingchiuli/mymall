package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.AttrGroupEntity;
import com.atguigu.mymall.product.vo.AttrGroupWithAttrsVo;
import com.atguigu.mymall.product.vo.SpuItemAttrGroupVo;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.AttrGroupEntity;
import com.atguigu.mymall.product.vo.AttrGroupWithAttrsVo;
import com.atguigu.mymall.product.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}

