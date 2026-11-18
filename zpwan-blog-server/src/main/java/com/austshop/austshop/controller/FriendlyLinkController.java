package com.austshop.austshop.controller;


import com.austshop.austshop.entity.FriendlyLinkInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.FriendlyLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "友情链接模块")
@RestController
@RequestMapping(value = "/blogManage/friendlylink")
public class FriendlyLinkController {

    @Autowired
    private FriendlyLinkService friendlyLinkService;

    /**
     * 新增
     *
     * @param name
     * @param url
     * @return
     */
    @ApiOperation(value="新增友情链接")
    @PostMapping(value = "/web/add")
    public ResultResponse handleFriendlyLinkAdd(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "url", required = true) String url,
            @RequestParam(value = "isShelves", required = false,defaultValue = "0") Integer isShelves
    ) {
        boolean flag = friendlyLinkService.getFriendlLinkInsert(name, url,isShelves);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 编辑
     *
     * @param id
     * @param name
     * @param ico
     * @param url
     * @param isShelves
     * @return
     */
    @ApiOperation(value="编辑友情链接")
    @PostMapping(value = "/update")
    public ResultResponse handleFriendlyLinkUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "ico", required = false) String ico,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "isShelves", required = false) Integer isShelves
    ) {
        boolean flag = friendlyLinkService.getFriendlyLinkUpdate(id, name, ico, url, isShelves);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value="友情链接详情")
    @GetMapping(value = "/getById")
    public ResultResponse handleFriendlyLinkInfo(
            @RequestParam(value = "id", required = true) Integer id
    ) {
        return new ResultResponse(200, "SUCCESS", friendlyLinkService.getFriendlyLinkInfo(id));
    }


    /**
     * 请求列表
     * @param page
     * @param limit
     * @param name
     * @param isShelves
     * @return
     */
    @ApiOperation(value="友情链接列表")
    @GetMapping(value = "/list")
    public ResultResponse handleFriendlyLinkList(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "isShelves", required = false) Integer isShelves
    ) {
        List<FriendlyLinkInfo> friendlyLinkInfos = friendlyLinkService.getFriendlyLinkList(page, limit, name, isShelves);
        int total = friendlyLinkService.getFriendlyLinkTotal(name, isShelves);
        ModelMap modelMap = new ModelMap();
        modelMap.put("total", total);
        modelMap.put("data", friendlyLinkInfos);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }
}
