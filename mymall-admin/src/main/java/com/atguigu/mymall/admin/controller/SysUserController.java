package com.atguigu.mymall.admin.controller;

import com.atguigu.mymall.admin.service.SysUserService;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;



    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {

        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("page", page);
    }

}
