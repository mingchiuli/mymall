package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.mymall.product.vo.AttrGroupRelationVo;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.mymall.product.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrGroupRelationVo> vos);
}

