package com.austshop.austshop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PictureInfo {

    private String picture_id;

    private String picture_url;

    private Long createDate;

    private String init_date;

    private Date create_time;

    private Date modify_time;
}
