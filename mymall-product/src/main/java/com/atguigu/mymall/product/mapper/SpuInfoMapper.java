package com.atguigu.mymall.product.mapper;

import com.atguigu.mymall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-21 11:53:38
 */
@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfoEntity> {

    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);


}
