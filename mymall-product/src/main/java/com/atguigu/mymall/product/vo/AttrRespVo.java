package com.atguigu.mymall.product.vo;

import lombok.Data;

/**
 * @author 孟享广
 * @date 2020-12-23 3:51 下午
 * @description
 */

@Data
public class AttrRespVo extends com.atguigu.mymall.product.vo.AttrVo {
    private String catelogName;
    private String groupName;

    private Long catelogPath[];
}
