package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.BannerInfo;

import java.util.List;

public interface IBannerService {

    boolean getBannerInsert(String title,String url,String imgUrl);

    boolean getBannerUpate(Integer id,String title,String url,String imrUrl,Integer isShelves);

    List<BannerInfo> getBannerList(Integer isShelves);

    BannerInfo getBannerInfoById(Integer id);
}
