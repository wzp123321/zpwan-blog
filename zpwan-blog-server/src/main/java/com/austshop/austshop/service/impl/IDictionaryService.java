package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.DictionaryInfo;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * zpwan
 * 2019/3/19
 */
public interface IDictionaryService {

    boolean getDictionaryInsert(String code,String value,Integer type,String description,Integer pId);

    List<DictionaryInfo> getDictionaryList(Integer page,Integer limit,Integer type,Integer pId);

    DictionaryInfo getDictionaryInfoById(Integer id);

    int getDictionaryTotal(Integer type);

    boolean getDicInfoDel(Integer id);

    boolean getDictionaryInfoUpdate(Integer id ,String code,String value,Integer type,String description,Integer pId);
}
