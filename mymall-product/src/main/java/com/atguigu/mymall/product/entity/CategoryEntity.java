package com.atguigu.mymall.product.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 商品三级分类
 * @TableName pms_category
 */
@TableName(value ="pms_category")
@Data
public class CategoryEntity implements Serializable {
    /**
     * 分类id
     */
    @TableId(type = IdType.AUTO)
    private Long catId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Long parentCid;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 逻辑删除
     * 是否显示[0-不显示，1显示]
     */
    @TableLogic(value = "1", delval = "0")
    private Integer showStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 计量单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private Integer productCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)//表示不在数据库表里
    private List<CategoryEntity> children;
}
