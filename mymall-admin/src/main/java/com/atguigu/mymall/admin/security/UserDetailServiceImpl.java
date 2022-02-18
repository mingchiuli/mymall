package com.atguigu.mymall.admin.security;

import com.atguigu.mymall.admin.entity.SysUser;
import com.atguigu.mymall.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author mingchiuli
 * @create 2022-01-24 10:09 PM
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("无此用户");
        }
        return new AccountUser(sysUser.getUserId(), sysUser.getUsername(), sysUser.getPassword(), getUserAuthority(sysUser.getUserId()));
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     * @param userId
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Long userId){

        // 角色(ROLE_admin)、菜单操作权限 sys:user:list
        String authority = sysUserService.getUserAuthorityInfo(userId);  // ROLE_admin,ROLE_normal,sys:user:list,....

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}

