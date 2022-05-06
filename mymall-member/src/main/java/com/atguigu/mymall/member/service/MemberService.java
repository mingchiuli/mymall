package com.atguigu.mymall.member.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.member.entity.MemberEntity;
import com.atguigu.mymall.member.exception.PhoneExistException;
import com.atguigu.mymall.member.exception.UsernameExistException;
import com.atguigu.mymall.member.vo.MemberLoginVo;
import com.atguigu.mymall.member.vo.MemberRegistVo;
import com.atguigu.mymall.member.vo.SocialUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 会员
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 11:47:42
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(MemberRegistVo vo) throws PhoneExistException, UsernameExistException;

    void checkPhoneUnique(String phone) throws PhoneExistException;

    void checkUsernameUnique(String username) throws UsernameExistException;

    MemberEntity login(MemberLoginVo vo);

    MemberEntity login(SocialUser vo) throws Exception;
}

