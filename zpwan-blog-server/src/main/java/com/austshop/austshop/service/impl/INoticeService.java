package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.NoticeInfo;

import java.util.List;

public interface INoticeService {
    boolean handleNoticeAdd(String content,int isEmergency);

    boolean handleNoticeDel(int id);

    List<NoticeInfo> getNoticeInfoList();

    boolean handleNoticeUpdate(int id,String content,int isEmergency);

    NoticeInfo getNoticeInfo(int id);
}
