package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FileInfo implements Serializable {
    private static final long serialVersionUID = -7918534838346707126L;
    //id
    private String id;
    // 文件名
    private String fileName;
    // md5值
    private String md5;
    // 地址
    private String url;
    // 长度
    private Long length;
    // 创建时间
    private Date create_time;
}