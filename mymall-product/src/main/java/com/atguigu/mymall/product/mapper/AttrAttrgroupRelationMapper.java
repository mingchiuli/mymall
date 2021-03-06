package com.atguigu.mymall.product.mapper;

import com.atguigu.mymall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
@Mapper
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchRelation(@Param("relationEntities") List<AttrAttrgroupRelationEntity> relationEntities);
}
