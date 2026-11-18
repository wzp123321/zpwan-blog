package com.austshop.austshop.service;

import com.austshop.austshop.entity.NoticeInfo;
import com.austshop.austshop.mapper.NoticeMapper;
import com.austshop.austshop.service.impl.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("noticeService")
public class NoticeService implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 新增通知
     * @param content
     * @param isEmergency
     * @return
     */
    public boolean handleNoticeAdd(String content, int isEmergency) {
        return noticeMapper.handleNoticeCreate(content,isEmergency) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean handleNoticeDel(int id) {
        return noticeMapper.handleNoticeDelete(id) > 0;
    }

    /**
     * 请求列表
     * @return
     */
    public List<NoticeInfo> getNoticeInfoList() {
        return noticeMapper.handleNoticeList();
    }

    /**
     * 更新
     * @param id
     * @param content
     * @param isEmergency
     * @return
     */
    public boolean handleNoticeUpdate(int id, String content, int isEmergency) {
        return noticeMapper.handleNoticeUpdate(id,content,isEmergency,new Date()) > 0;
    }

    /**
     * 请求详情
     * @param id
     * @return
     */
    public NoticeInfo getNoticeInfo(int id) {
        return noticeMapper.getNoticeInfoById(id);
    }
}
