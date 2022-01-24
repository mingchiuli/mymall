package com.atguigu.mymall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.mymall.admin.entity.SysUser;
import com.atguigu.mymall.admin.service.SysUserService;
import com.atguigu.mymall.admin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author mingchiuli
* @description 针对表【sys_user(系统用户)】的数据库操作Service实现
* @createDate 2022-01-24 22:39:14
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public SysUser getByUsername(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {
        return null;
    }
}




