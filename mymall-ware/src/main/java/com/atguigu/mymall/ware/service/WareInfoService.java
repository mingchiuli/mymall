package com.atguigu.mymall.ware.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.ware.entity.WareInfoEntity;
import com.atguigu.mymall.ware.vo.FareVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:15:27
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    FareVo getFare(Long addrId);
}

