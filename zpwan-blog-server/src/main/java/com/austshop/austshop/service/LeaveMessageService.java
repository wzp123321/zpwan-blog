package com.austshop.austshop.service;

import com.alibaba.fastjson.JSONObject;
import com.austshop.austshop.entity.LeaveMessageInfo;
import com.austshop.austshop.entity.UserInfo;
import com.austshop.austshop.mapper.LeaveMessageMapper;
import com.austshop.austshop.service.impl.ILeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("leaveMessageService")
public class LeaveMessageService implements ILeaveMessageService {
    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    /**
     * 新增留言
     *
     * @param content
     * @param author
     * @return
     */
    public boolean getLeaveMessageInsert(String content, String author) {
        LeaveMessageInfo leaveMessageInfo = new LeaveMessageInfo();
        leaveMessageInfo.setLeave_message_id(UUID.randomUUID().toString().toLowerCase());
        leaveMessageInfo.setContent(content);
        leaveMessageInfo.setAuthor(author);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());                    //放入Date类型数据

        int year = calendar.get(Calendar.YEAR);                    //获取年份
        int month = calendar.get(Calendar.MONTH) + 1;               //获取月份
        int day = calendar.get(Calendar.DATE);                      // 获取日期
        leaveMessageInfo.setInit_date(year + "-" + month + "-" + day);

        leaveMessageInfo.setCreate_time(new Date());
        leaveMessageInfo.setModify_time(new Date());
        return leaveMessageMapper.getLeaveMessageInsert(leaveMessageInfo) > 0;
    }

    /**
     * 获取列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<LeaveMessageInfo> getLeaveMessageList(int page, int limit, Integer is_shelves, Integer is_good) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        List<LeaveMessageInfo> leaveMessages = leaveMessageMapper.getLeaveMessageList(skip, limit, is_shelves, is_good);
        for (int i = 0; i < leaveMessages.size(); i++) {
            leaveMessages.get(i).setAuthor(JSONObject.parseObject(String.valueOf(leaveMessages.get(i).getAuthor()), UserInfo.class));
        }

        return leaveMessages;
    }

    /**
     * 获取总数
     *
     * @return
     */
    public int getLeaveMessageTotal(Integer is_shelves, Integer is_good) {
        return leaveMessageMapper.getLeaveMessageTotal(is_shelves, is_good);
    }

    /**
     * 上下架
     *
     * @param leave_message_id
     * @param is_shelves
     * @return
     */
    public boolean getLeaveMessageShelves(String leave_message_id, int is_shelves) {
        return leaveMessageMapper.getLeaveMessageShelves(leave_message_id, is_shelves) > 0;
    }

    /**
     * 设置为精品
     *
     * @param leave_message_id
     * @param is_good
     * @return
     */
    public boolean getLeaveMessageGood(String leave_message_id, int is_good) {
        return leaveMessageMapper.getLeaveMessageGood(leave_message_id, is_good) > 0;
    }

    /**
     * 删除
     *
     * @param leave_message_id
     * @return
     */
    public boolean getLeaveMessageDelete(String leave_message_id) {
        return leaveMessageMapper.getLeaveMessageDelete(leave_message_id) > 0;
    }


}
