package com.austshop.austshop.controller;

import com.austshop.austshop.entity.LeaveMessageInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.LeaveMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(description = "留言模块")
@RestController
@RequestMapping(value = "/blogManage/leavemessage")
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 新增留言
     *
     * @param content
     * @param author
     * @return
     */
    @ApiOperation(value = "新增留言")
    @PostMapping("/web/add")
    public ResultResponse handleLeaveMessageInsert(
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "author", required = true) String author
    ) {
        boolean flag = leaveMessageService.getLeaveMessageInsert(content, author);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 查询列表
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "查询列表")
    @GetMapping("/web/list")
    public ResultResponse handleLeaveMessageList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "is_shelves", required = false) Integer is_shelves,
            @RequestParam(value = "is_good", required = false) Integer is_good
    ) {
        List<LeaveMessageInfo> leaveMessageInfos = leaveMessageService.getLeaveMessageList(page, limit, is_shelves, is_good);
        int total = leaveMessageService.getLeaveMessageTotal(is_shelves, is_good);
        ModelMap modelMap = new ModelMap();
        modelMap.put("total", total);
        modelMap.put("data", leaveMessageInfos);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 留言上下架
     *
     * @param leave_message_id
     * @param is_shelves
     * @return
     */
    @ApiOperation(value = "留言上下架")
    @PostMapping("/isShelves")
    public ResultResponse handleLeaveMessageShelves(
            @RequestParam(value = "leave_message_id", required = true) String leave_message_id,
            @RequestParam(value = "is_shelves", required = true) int is_shelves
    ) {
        boolean flag = leaveMessageService.getLeaveMessageShelves(leave_message_id, is_shelves);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 精品留言
     *
     * @param leave_message_id
     * @param is_good
     * @return
     */
    @ApiOperation(value = "精品留言")
    @PostMapping("/isGood")
    public ResultResponse handleLeaveMessageGood(
            @RequestParam(value = "leave_message_id", required = true) String leave_message_id,
            @RequestParam(value = "is_good", required = true) int is_good
    ) {
        boolean flag = leaveMessageService.getLeaveMessageGood(leave_message_id, is_good);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 删除留言
     *
     * @param leave_message_id
     * @return
     */
    @ApiOperation(value = "删除留言")
    @PostMapping("/delete")
    public ResultResponse handleLeaveMessageDelete(
            @RequestParam(value = "leave_message_id", required = true) String leave_message_id
    ) {
        boolean flag = leaveMessageService.getLeaveMessageDelete(leave_message_id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
