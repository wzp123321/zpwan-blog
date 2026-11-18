package com.austshop.austshop.controller;

import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.entity.TagInfo;
import com.austshop.austshop.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "标签模块")
@RestController
@RequestMapping(value = "/blogManage/tag")
public class TagController {

    @Autowired
    public TagService tagService;

    /**
     * 新增标签
     * @param code
     * @param value
     * @return
     */
    @ApiOperation(value="新增标签")
    @PostMapping(value = "/add")
    public ResultResponse handleTagInsert(
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "value", required = true) String value
    ) {
        boolean flag = tagService.getTagInsert(code, value);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 查询标签列表
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="查询标签列表")
    @GetMapping(value = "/list")
    public ResultResponse handleTagSelect(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit
    ) {
        int total = tagService.getTagListTotal();
        ModelMap modelMap = new ModelMap();
        List<TagInfo> list = tagService.getTagList(page, limit);
        modelMap.put("total", total);
        modelMap.put("data", list);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @ApiOperation(value="删除标签")
    @PostMapping(value = "delete")
    public ResultResponse handleTagDelete(
            @RequestParam(value = "id", required = true) Integer id
    ) {
        boolean flag = tagService.getTagDelete(id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
