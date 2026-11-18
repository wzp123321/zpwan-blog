package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskInfo implements Serializable {
    private static final long serialVersionUID = 3412857943327948782L;
    // id
    private String task_id;
    // 内容
    private String title;
    // 优先级
    private Integer priority;
    // 是否完成
    private Integer is_complete;
    // 截止日期
    private String endline;
    // 创建时间
    private Date create_time;
    // 修改时间
    private Date modify_time;
}
