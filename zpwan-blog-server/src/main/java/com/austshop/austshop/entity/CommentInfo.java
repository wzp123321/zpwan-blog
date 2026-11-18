package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentInfo implements Serializable {
    private static final long serialVersionUID = 3401442237228430380L;
    // 评论id
    private String id;
    // 评论的作者
    private Object author;
    // 文章id
    private String article_id;
    // 内容
    private String content;
    //是否被删除 0：否 1：是
    private Integer is_delete;
    // 点赞数组
    private Integer ups;
    // 点踩数组
    private Integer downs;
    // 回复的评论id
    private String parent_id;
    // 是否为一级评论--无父级
    private String is_root;
    // 回复评论的用户信息
    private Object reply_userInfo;
    // 创建时间
    private Date create_time;
}
