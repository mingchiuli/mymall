package com.atguigu.mymall.ware.service;

import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.ware.entity.WareOrderTaskDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:15:27
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

