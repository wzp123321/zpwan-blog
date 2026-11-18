package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.PictureInfo;

import java.util.Date;
import java.util.List;

public interface IPictureService {
    boolean handlePictureInsert(String picture_url);

    List<PictureInfo> queryPictureList(Integer page, Integer limit, Long startDate, Long endDate);

    boolean checkPictureListHasNext(Integer page, Integer limit, Long startDate, Long endDate);

    int queryPictureListTotal(Long startDate, Long endDate);

    boolean handlePictureDelete(String picture_id);
}
