package com.austshop.austshop.controller;

import com.austshop.austshop.entity.CommentInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.entity.UserInfo;
import com.austshop.austshop.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "文章评论模块")
@RequestMapping(value = "/blogManage/comment")
@RestController
public class CommentController {
    //https://gitee.com/yadong.zhang/DBlog/tree/master/

    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * @param author
     * @param content
     * @param article_id
     * @param parent_id
     * @param is_root
     * @param reply_userInfo
     * @return
     */
    @ApiOperation(value="新增评论")
    @PostMapping(value = "/web/add")
    public ResultResponse handlCommentAdd(
            @RequestParam(value = "author", required = true) String author,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "article_id", required = true) String article_id,
            @RequestParam(value = "parent_id", required = true) String parent_id,
            @RequestParam(value = "is_root", required = true) Boolean is_root,
            @RequestParam(value = "reply_userInfo", required = true) String reply_userInfo

    ) {
        boolean flag = commentService.getCommentInsert(article_id, author, content, parent_id, is_root, reply_userInfo);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 查询评论详情
     * @param id
     * @return
     */
    @ApiOperation(value="查看评论详情")
    @GetMapping(value = "/getById")
    public ResultResponse hanleCommentInfoById(
            @RequestParam(value = "id", required = true) String id

    ) {
        return new ResultResponse(200, "SUCCESS", commentService.getCommentInfoById(id));
    }

    /**
     * 查询评论列表
     * @param page
     * @param limit
     * @param article_id
     * @return
     */
    @ApiOperation(value="查询评论列表")
    @GetMapping(value = "/list")
    public ResultResponse hanleCommentList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "article_id", required = false) String article_id

    ) {
        ModelMap modelMap = new ModelMap();
        int total = commentService.getCommentTotal(article_id);
        List<CommentInfo> list = commentService.getCommentList(page,limit,article_id);
        modelMap.put("total",total);
        modelMap.put("data",list);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @ApiOperation(value="删除评论")
    @PostMapping(value = "/delete")
    public ResultResponse hanleCommentDelete(
            @RequestParam(value = "id",required = true) String id
    ) {
        boolean flag = commentService.handleCommentDelete(id);
        return new ResultResponse(200, flag?"SUCCESS":"FAIL", flag);
    }
}
