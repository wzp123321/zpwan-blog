package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.FriendlyLinkInfo;

import java.util.List;

public interface IFriendlyLinkService {

    boolean getFriendlLinkInsert(String name,String url,Integer isShelves);

    List<FriendlyLinkInfo> getFriendlyLinkList(Integer page,Integer limit,String name,Integer isShelves);

    FriendlyLinkInfo getFriendlyLinkInfo(Integer id);

    boolean getFriendlyLinkUpdate(Integer id,String name,String ico,String url,Integer isShelves);

    int getFriendlyLinkTotal(String name,Integer isShelves);
}
