package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MusicInfo implements Serializable {
    private static final long serialVersionUID = 2266913218808440491L;

    private String id;

    private String title;

    private String author;

    private String pic;

    private String src;
}
