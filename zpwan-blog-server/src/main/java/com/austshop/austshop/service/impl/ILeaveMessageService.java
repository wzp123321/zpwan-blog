package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.LeaveMessageInfo;

import java.util.List;

public interface ILeaveMessageService {
    boolean getLeaveMessageInsert(String content, String author);

    List<LeaveMessageInfo> getLeaveMessageList(int page, int limit, Integer is_shelves, Integer is_good);

    int getLeaveMessageTotal(Integer is_shelves, Integer is_good);

    boolean getLeaveMessageShelves(String leave_message_id, int is_shelves);

    boolean getLeaveMessageGood(String leave_message_id, int is_good);

    boolean getLeaveMessageDelete(String leave_message_id);
}
