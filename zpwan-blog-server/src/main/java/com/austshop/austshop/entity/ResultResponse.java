package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultResponse implements Serializable {
    private static final long serialVersionUID = -6382256217614978715L;
    //    响应码
    private Integer code = 200;
    //    响应信息
    private String Message;
    //    响应值
    private Object data;

    public ResultResponse() {
    }

    public ResultResponse(Integer code, String message) {
        this.code = code;
        Message = message;
    }

    public ResultResponse(Integer code, String message, Object data) {
        this.code = code;
        Message = message;
        this.data = data;
    }
}
