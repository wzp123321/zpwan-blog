package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class MessageInfo {
    // 消息类型 1:新增  2:更新
    public Integer type;

    public String article_id;

    public String title;
}
