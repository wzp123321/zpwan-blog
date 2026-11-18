package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -7888429584371866511L;
    //    用户id
    private String user_id;
    //    用户名
    private String name;
    //    用户头像
    private String avatar_url;
    //    用户地址
    private String location;
}
