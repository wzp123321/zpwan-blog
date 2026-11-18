package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TagInfo implements Serializable {
    private static final long serialVersionUID = 9146941449464662303L;
    //    id
    private Integer id;
    //    code值
    private String code;
    //    value
    private String value;
    //    创建时间
    private Date create_time;
    //    修改时间
    private Date modify_time;
}
