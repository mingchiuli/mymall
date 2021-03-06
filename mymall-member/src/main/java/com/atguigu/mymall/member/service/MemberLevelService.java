package com.atguigu.mymall.member.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 会员等级
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:47:41
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

