package com.atguigu.mymall.product.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.AttrEntity;
import com.atguigu.mymall.product.vo.AttrGroupRelationVo;
import com.atguigu.mymall.product.vo.AttrRespVo;
import com.atguigu.mymall.product.vo.AttrVo;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.product.entity.AttrEntity;
import com.atguigu.mymall.product.vo.AttrGroupRelationVo;
import com.atguigu.mymall.product.vo.AttrRespVo;
import com.atguigu.mymall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attrVo);

    List<AttrEntity> getRelationAttr(Long catelogId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    /**
     * 在指定的所有属性集合里 挑出检索属性
     * @param attrIds
     * @return
     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

