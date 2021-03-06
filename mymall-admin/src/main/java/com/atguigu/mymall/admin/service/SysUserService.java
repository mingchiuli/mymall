package com.atguigu.mymall.admin.service;

import com.atguigu.mymall.admin.entity.SysUser;
import com.atguigu.mymall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author mingchiuli
* @description 针对表【sys_user(系统用户)】的数据库操作Service
* @createDate 2022-01-24 22:39:14
*/
public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);

    String getUserAuthorityInfo(Long userId);

    PageUtils queryPage(Map<String, Object> params);

}
