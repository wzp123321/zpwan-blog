package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典 实体类
 */
@Data
public class DictionaryInfo implements Serializable {
    private static final long serialVersionUID = 6306030426681660785L;
    private Integer id;
    private String code;
    private String value;
    private Integer type;
    private String description;
    private Integer pId;
    private String pCode;
    private String pValue;
    private Date create_time;
    private Date modify_time;
}
