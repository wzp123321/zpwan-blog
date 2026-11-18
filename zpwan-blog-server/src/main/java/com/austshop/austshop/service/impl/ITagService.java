package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.TagInfo;

import java.util.List;

public interface ITagService {

    boolean getTagInsert(String code,String value);

    List<TagInfo> getTagList(Integer page,Integer limit);

    int getTagListTotal();

    boolean getTagDelete(Integer id);
}
