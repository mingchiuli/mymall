package com.atguigu.mymall.member.mapper;

import com.atguigu.mymall.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:47:41
 */
@Mapper
public interface MemberLevelMapper extends BaseMapper<MemberLevelEntity> {

    MemberLevelEntity getDefaultLevel();
}
