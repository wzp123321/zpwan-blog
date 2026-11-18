package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WinXinEntity implements Serializable {
    private static final long serialVersionUID = 1856743401354967827L;

    private String shareUrl;
    private String appId;
    private String access_token;
    private String ticket;
    private String nonceStr;
    private String timestamp;
    private String signature;

}
