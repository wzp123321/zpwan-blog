package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminInfo implements Serializable {
    private static final long serialVersionUID = -1142535860217362549L;

    // 管理员id
    private String admin_id;
    // 管理员姓名
    private String admin_name;
    // 密码
    private String admin_password;
    //角色 1:普通管理员  2:超级管理员
    private Integer role;
    // 头像
    private String avatar_url;
    // 创建时间
    private Date create_time;
    // 修改时间
    private Date modify_time;

}