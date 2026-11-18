package com.austshop.austshop.controller;


import com.austshop.austshop.entity.MusicInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "音乐模块")
@RestController
@RequestMapping(value = "/blogManage/music")
public class MusicController {

    @Autowired
    public MusicService musicService;


    @ApiOperation(value = "新增音乐")
    @PostMapping(value = "/add")
    public ResultResponse handleMusicCreate(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "src", required = true) String src,
            @RequestParam(value = "pic", required = true) String pic,
            @RequestParam(value = "author", required = true) String author
    ) {
        boolean flag = musicService.handleMusicCreate(title, author, src, pic);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    @ApiOperation(value = "编辑音乐")
    @PostMapping(value = "/update")
    public ResultResponse handleMusicUpdate(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "src", required = true) String src,
            @RequestParam(value = "pic", required = true) String pic,
            @RequestParam(value = "author", required = true) String author
    ) {
        boolean flag = musicService.handleMusicUpdate(id, title, author, src, pic);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    @ApiOperation(value = "音乐列表")
    @GetMapping(value = "/list")
    public ResultResponse getMusicList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author
    ) {
        ModelMap modelMap = new ModelMap();
        List<MusicInfo> musicInfos = musicService.getMusicList(page, limit, title, author);
        int total = musicService.getMusicListCount(title, author);
        modelMap.put("data", musicInfos);
        modelMap.put("total", total);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping(value = "/getById")
    public ResultResponse getMusicInfoById(
            @RequestParam(value = "id", required = true) String id
    ) {
        MusicInfo musicInfo = musicService.getMusicInfoById(id);
        return new ResultResponse(200, "SUCCESS", musicInfo);
    }

    @ApiOperation(value = "删除音乐")
    @PostMapping(value = "/delete")
    public ResultResponse getMusicDeleteById(
            @RequestParam(value = "id", required = true) String id
    ) {
        boolean flag = musicService.handleMusicDelete(id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
