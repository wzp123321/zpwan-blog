package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FriendlyLinkInfo implements Serializable {
    private static final long serialVersionUID = -3979700592935184184L;
    //    id
    private Integer id;
    //    链接名称
    private String name;
    //    ico 通过域名/favicon.ico获取  如果获取不到就给个默认的
    private String ico;
    //    链接地址
    private String url;
    //    是否上架 0:否  1:是
    private Integer isShelves;
    //    创建时间
    private Date create_time;
    //    修改时间
    private Date modify_time;
}
