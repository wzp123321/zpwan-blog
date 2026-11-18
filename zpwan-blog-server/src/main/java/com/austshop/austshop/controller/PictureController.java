package com.austshop.austshop.controller;


import com.austshop.austshop.entity.PictureInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(description = "照片模块")
@RestController
@RequestMapping(value = "/blogManage/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * 新增照片
     *
     * @param urls
     * @return
     */
    @ApiOperation(value = "新增照片")
    @PostMapping(value = "/web/add")
    public ResultResponse handlePictureInsert(
            @RequestParam(value = "urls", required = true) String urls
    ) {
        boolean flag = pictureService.handlePictureInsert(urls);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @return
     */
    @ApiOperation(value = "查询列表")
    @GetMapping(value = "/list")
    public ResultResponse handlePictureList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "startDate", required = true) Long startDate,
            @RequestParam(value = "endDate", required = true) Long endDate
    ) {
        List<PictureInfo> pictureInfoList = pictureService.queryPictureList(page, limit, startDate, endDate);
        int total = pictureService.queryPictureListTotal(startDate, endDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        boolean hasNext = pictureService.checkPictureListHasNext(page+1, limit, calendar.getTime().getTime(), endDate);

        ModelMap modelMap = new ModelMap();
        modelMap.put("data", pictureInfoList);
        modelMap.put("total", total);
        modelMap.put("hasNext", hasNext);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 删除照片
     * @param picture_id
     * @return
     */
    @ApiOperation(value = "删除照片")
    @PostMapping(value = "delete")
    public ResultResponse handlePictureDelete(
            @RequestParam(value = "picture_id", required = true) String picture_id
    ) {
        boolean flag = pictureService.handlePictureDelete(picture_id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
