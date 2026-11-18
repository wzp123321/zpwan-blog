package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BannerInfo implements Serializable {
    private static final long serialVersionUID = -3733675358799856488L;
    //    id
    private Integer id;
    //    标题
    private String title;
    //    封面图片
    private String imgUrl;
    //    地址
    private String url;
    //    是否上架  0:否  1:是
    private Integer isShelves;
    //    创建时间
    private Date create_time;
    //    修改时间
    private Date modify_time;
}
