package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户点赞文章
 */
@Data
public class UserUpsInfo implements Serializable {
    private static final long serialVersionUID = 7473815157782645573L;
    //    id
    private Integer id;
    //    文章id
    private String article_id;
    //    点赞用户id
    private String user_id;
    //    创建时间
    private Date create_time;
    //    修改时间
    private Date modify_time;
}
