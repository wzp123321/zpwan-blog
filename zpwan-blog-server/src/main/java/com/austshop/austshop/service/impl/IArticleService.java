package com.austshop.austshop.service.impl;


import com.austshop.austshop.entity.ArticleInfo;

import java.util.Date;
import java.util.List;

public interface IArticleService {

    List<ArticleInfo> getArticleList(Integer page, Integer limit, String key, String title, Integer first_catalogId, Integer second_catalogId, Integer isShelves, Integer isRecommend);

    List<ArticleInfo> getWebArticleList(Integer page, Integer limit, Integer second_catalogId);

    List<ArticleInfo> getHotArticleList();

    int getArticleTotal(String key, String title, Integer first_catalogId, Integer second_catalogId, Integer isShelves, Integer isRecommend);

    int getWebArticleTotal(Integer second_catalogId);

    boolean getArticleAdd(String title, String content, String md, String description, String imgUrl, String tags, String quotes, Integer first_catalogId, Integer second_catalogId);

    boolean getArticleInfoUpdate(String id, String title, String content, String md, String description, String imgUrl, String tags, String quotes, Integer first_catalogId, Integer second_catalogId);

    boolean getArticleTop(String id, Integer isTop);

    boolean getArticleDelete(String id);

    boolean getArticleIsShelves(String id, Integer isShelves);

    boolean getArticleRecommend(String id, Integer isRecommend);

    ArticleInfo getArticleInfoById(String id);

    boolean getArticleVisit(String id);

    boolean getArticleLike(String id);

}
