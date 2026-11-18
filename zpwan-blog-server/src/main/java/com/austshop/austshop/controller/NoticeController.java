package com.austshop.austshop.controller;

import com.austshop.austshop.entity.NoticeInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "通知模块")
@RestController
@RequestMapping(value="/blogManage/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 新增通知
     * @param content
     * @param isEmergency
     * @return
     */
    @ApiOperation(value="新增通知")
    @PostMapping(value="/add")
    public ResultResponse handleNoticeInsert(
            @RequestParam(value="content",required = true) String content,
            @RequestParam(value="isEmergency",required = true) int isEmergency
    ){
        boolean flag = noticeService.handleNoticeAdd(content,isEmergency);
        return new ResultResponse(200,flag? "添加成功":"添加失败",flag);
    }

    /**
     * 删除通知
     * @param id
     * @return
     */
    @ApiOperation(value="删除通知")
    @GetMapping(value="/delete")
    public ResultResponse handleNoticeDelete(
            @RequestParam(value="id",required = true) int id
    ){
        boolean flag = noticeService.handleNoticeDel(id);
        return new ResultResponse(200,flag? "删除成功":"删除失败",flag);
    }

    /**
     * 更新通知
     * @param id
     * @param content
     * @param isEmergency
     * @return
     */
    @ApiOperation(value="更新通知")
    @PostMapping(value="/update")
    public ResultResponse handleNoticeUpdate(
            @RequestParam(value="id",required = true) int id,
            @RequestParam(value="content",required = true) String content,
            @RequestParam(value="isEmergency",required = true) int isEmergency
    ){
        boolean flag = noticeService.handleNoticeUpdate(id,content,isEmergency);
        return new ResultResponse(200,flag? "修改成功":"修改失败",flag);
    }

    /**
     * 查询通知列表
     * @return
     */
    @ApiOperation(value="查询通知列表")
    @GetMapping(value="/web/list")
    public ResultResponse handleNoticeList(
    ){
        List<NoticeInfo> noticeInfos = noticeService.getNoticeInfoList();
        ModelMap modelMap = new ModelMap();
        modelMap.put("data",noticeInfos);
        return new ResultResponse(200,"SUCCESS",modelMap);
    }

    /**
     * 获取通知详情
     * @param id
     * @return
     */
    @ApiOperation(value="获取通知详情")
    @GetMapping(value="/getById")
    public ResultResponse handleNoticeInfoById(
            @RequestParam(value="id" ,required = true) int id
    ){
        return new ResultResponse(200,"SUCCESS",noticeService.getNoticeInfo(id));
    }
}
