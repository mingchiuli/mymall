package com.atguigu.mymall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ÊôÐÔ&ÊôÐÔ·Ö×é¹ØÁª
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:03:44
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class PmsAttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ÊôÐÔid
	 */
	private Long attrId;
	/**
	 * ÊôÐÔ·Ö×éid
	 */
	private Long attrGroupId;
	/**
	 * ÊôÐÔ×éÄÚÅÅÐò
	 */
	private Integer attrSort;

}
