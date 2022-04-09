package com.atguigu.mymall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ÉÌÆ·ÆÀ¼Û»Ø¸´¹ØÏµ
 *
 * @author mxg
 * @email mxg@gmail.com
 * @date 2020-12-16 12:03:44
 */
@Data
@TableName("pms_comment_replay")
public class PmsCommentReplayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ÆÀÂÛid
	 */
	private Long commentId;
	/**
	 * »Ø¸´id
	 */
	private Long replyId;

}
