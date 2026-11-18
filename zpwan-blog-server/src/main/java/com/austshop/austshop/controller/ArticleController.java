package com.austshop.austshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.austshop.austshop.entity.ArticleInfo;
import com.austshop.austshop.entity.MessageInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Api(description = "文章模块接口")
@RestController
@Slf4j
@RequestMapping(value = "blogManage/article")
public class ArticleController {
    //    https://blog.csdn.net/qq_35712465/article/details/102256785
//      https://gitee.com/yadong.zhang/JustAuth
    @Autowired
    private ArticleService articleService;

    /**
     * 动态查询
     *
     * @param page
     * @param limit
     * @param title
     * @param first_catalogId
     * @param second_catalogId
     * @param isShelves
     * @return
     */
    @ApiOperation(value = "后台查询文章列表")
    @GetMapping(value = "/list")
    public ResultResponse getArticleList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "first_catalogId", required = false) Integer first_catalogId,
            @RequestParam(value = "second_catalogId", required = false) Integer second_catalogId,
            @RequestParam(value = "isShelves", required = false) Integer isShelves,
            @RequestParam(value = "isRecommend", required = false) Integer isRecommend

    ) {
        ModelMap modelMap = new ModelMap();
        int total = articleService.getArticleTotal(key, title, first_catalogId, second_catalogId, isShelves, isRecommend);
        modelMap.put("data", articleService.getArticleList(page, limit, key, title, first_catalogId, second_catalogId, isShelves, isRecommend));
        modelMap.put("total", total);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * web端请求列表
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "前台文章列表")
    @GetMapping(value = "/web/list")
    public ResultResponse getWebArticleList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "second_catalogId", required = false) Integer second_catalogId
    ) {
        ModelMap modelMap = new ModelMap();
        int total = articleService.getWebArticleTotal(second_catalogId);
        modelMap.put("data", articleService.getWebArticleList(page, limit, second_catalogId));
        modelMap.put("total", total);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * web端请求热门列表
     *
     * @return
     */
    @ApiOperation(value = "查询热门文章")
    @GetMapping(value = "/web/hotlist")
    public ResultResponse getWebHotArticleList(
    ) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("data", articleService.getHotArticleList());
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 新增文章
     *
     * @param title
     * @param content
     * @param md
     * @param imgUrl
     * @param description
     * @param tags
     * @param quotes
     * @param first_catalogId
     * @param second_catalogId
     * @return
     */
    @ApiOperation(value = "新增文章")
    @PostMapping(value = "/add")
    public ResultResponse handleArticleCreate(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "md", required = true) String md,
            @RequestParam(value = "imgUrl", required = true) String imgUrl,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam(value = "tags", required = true) String tags,
            @RequestParam(value = "quotes", required = true) String quotes,
            @RequestParam(value = "first_catalogId", required = true) Integer first_catalogId,
            @RequestParam(value = "second_catalogId", required = true) Integer second_catalogId
    ) {
        boolean res = articleService.getArticleAdd(title, content, md, description, imgUrl, tags, quotes, first_catalogId, second_catalogId);
        return new ResultResponse(200, res ? "SUCCESS" : "FAIL", res);
    }

    /**
     * 文章更新
     *
     * @param id
     * @param title
     * @param content
     * @param md
     * @param imgUrl
     * @param description
     * @param tags
     * @param quotes
     * @param first_catalogId
     * @param second_catalogId
     * @return
     */
    @ApiOperation(value = "文章更新")
    @PostMapping(value = "/update")
    public ResultResponse handleArticleInfoUpdate(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "md", required = false) String md,
            @RequestParam(value = "imgUrl", required = false) String imgUrl,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "quotes", required = false) String quotes,
            @RequestParam(value = "first_catalogId", required = false) Integer first_catalogId,
            @RequestParam(value = "second_catalogId", required = false) Integer second_catalogId
    ) {
        boolean res = articleService.getArticleInfoUpdate(id, title, content, md, description, imgUrl, tags, quotes, first_catalogId, second_catalogId);
        return new ResultResponse(200, res ? "SUCCESS" : "FAIL", res);
    }

    /**
     * 根据id获取详细信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询文章详情")
    @GetMapping(value = "/getById")
    public ResultResponse handleArticleInfoById(
            @RequestParam(value = "id", required = true) String id
    ) {
        log.info("进入handleArticleInfoById方法");
        ArticleInfo articleInfo = articleService.getArticleInfoById(id);

        return new ResultResponse(200, "SUCCESS", articleInfo);
    }

    /**
     * 文章置顶
     *
     * @param id
     * @return
     * @Param top 1:取消置顶   0：置顶
     */
    @ApiOperation(value = "文章置顶")
    @GetMapping(value = "/top")
    public ResultResponse getArticleTop(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "top", required = true) Integer top
    ) {
        return new ResultResponse(200, "SUCCESS", articleService.getArticleTop(id, top));
    }

    /**
     * 文章删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "文章删除")
    @GetMapping(value = "/delete")
    public ResultResponse getArticleDelete(
            @RequestParam(value = "id", required = true) String id
    ) {
        return new ResultResponse(200, "SUCCESS", articleService.getArticleDelete(id));
    }

    /**
     * 文章推荐
     *
     * @param id
     * @return
     * @Param recommend 1:取消推荐 0：推荐
     */
    @ApiOperation(value = "文章推荐")
    @GetMapping(value = "/recommend")
    public ResultResponse getArticleRecommend(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "recommend", required = true) Integer recommend
    ) {
        return new ResultResponse(200, "SUCCESS", articleService.getArticleRecommend(id, recommend));
    }

    /**
     * 文章上下架
     *
     * @param id
     * @return
     * @Param shelves 1:下架  0：上架
     */
    @ApiOperation(value = "文章上下架")
    @GetMapping(value = "/shelves")
    public ResultResponse getArticleShelves(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "shelves", required = true) Integer shelves
    ) {
        return new ResultResponse(200, "SUCCESS", articleService.getArticleIsShelves(id, shelves));
    }

    /**
     * 文章访问
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "文章访问记录")
    @GetMapping(value = "/visit")
    public ResultResponse getArticleVisit(
            @RequestParam(value = "id", required = true) String id
    ) {
        WebSocketSession webSocketSession;

        return new ResultResponse(200, "SUCCESS", articleService.getArticleVisit(id));
    }

}
