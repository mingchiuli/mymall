package com.atguigu.mymall.member.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.member.entity.MemberReceiveAddressEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 会员收货地址
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:47:42
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MemberReceiveAddressEntity> getAddress(Long memberId);
}

