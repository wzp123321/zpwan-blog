package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知
 */
@Data
public class NoticeInfo implements Serializable {
    private static final long serialVersionUID = -5613118757383475642L;
    private Integer id;

    private String content;

    private Integer isEmergency;

    private Date create_time;

    private Date modify_time;
}
