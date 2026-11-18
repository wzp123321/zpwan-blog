package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LeaveMessageInfo implements Serializable {

    private static final long serialVersionUID = -9071044174309552178L;

    private String leave_message_id;

    private String content;

    //留言作者
    private Object author;

    private Integer is_delete;

    private Integer is_shelves;

    // 是否是精品
    private Integer is_good;

    // 年-月-日
    private String init_date;

    private Date create_time;

    private Date modify_time;
}
