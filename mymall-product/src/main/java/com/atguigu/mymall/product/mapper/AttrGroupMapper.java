package com.atguigu.mymall.product.mapper;

import com.atguigu.mymall.product.entity.AttrGroupEntity;
import com.atguigu.mymall.product.vo.SpuItemAttrGroupVo;
import com.atguigu.mymall.product.entity.AttrGroupEntity;
import com.atguigu.mymall.product.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
@Mapper
public interface AttrGroupMapper extends BaseMapper<AttrGroupEntity> {

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
