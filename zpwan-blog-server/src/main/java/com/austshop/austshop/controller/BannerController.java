package com.austshop.austshop.controller;


import com.austshop.austshop.entity.BannerInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "banner模块")
@RestController
@RequestMapping(value = "/blogManage/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 新增banner
     * @param title
     * @param url
     * @param imgUrl
     * @return
     */
    @ApiOperation(value="新增banner")
    @PostMapping(value = "/add")
    public ResultResponse handleBannerAdd(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "url", required = true) String url,
            @RequestParam(value = "imgUrl", required = true) String imgUrl
    ) {
        boolean flag = bannerService.getBannerInsert(title, url, imgUrl);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * banner更新
     * @param id
     * @param title
     * @param url
     * @param imgUrl
     * @param isShelves
     * @return
     */
    @ApiOperation(value="banner更新")
    @PostMapping(value = "/update")
    public ResultResponse handleBannerUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "imgUrl", required = false) String imgUrl,
            @RequestParam(value = "isShelves", required = false) Integer isShelves
    ) {
        boolean flag = bannerService.getBannerUpate(id, title, url, imgUrl, isShelves);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * banner列表
     * @param isShelves
     * @return
     */
    @ApiOperation(value="查询banner列表")
    @GetMapping(value = "/list")
    public ResultResponse handleBannerList(
            @RequestParam(value = "isShelves", required = false) Integer isShelves
    ) {

        ModelMap modelMap = new ModelMap();
        List<BannerInfo> bannerInfos = bannerService.getBannerList(isShelves);
        modelMap.put("data", bannerInfos);
        return new ResultResponse(200, "success", modelMap);
    }

    /**
     * 查询banner详情
     * @param id
     * @return
     */
    @ApiOperation(value="banner详情")
    @GetMapping(value = "/getById")
    public ResultResponse handleBannerInfoById(
            @RequestParam(value = "id", required = true) Integer id
    ) {
        BannerInfo bannerInfo = bannerService.getBannerInfoById(id);
        return new ResultResponse(200, "success", bannerInfo);
    }
}
