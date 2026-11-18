package com.austshop.austshop.controller;

import com.austshop.austshop.entity.DictionaryInfo;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "字典模块")
@RestController
@RequestMapping(value = "/blogManage/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 添加字典
     * @param code
     * @param value
     * @param type
     * @param description
     * @param pId
     * @return
     */
    @ApiOperation(value="添加字典")
    @PostMapping(value = "/add")
    public ResultResponse handleDictionaryAdd(
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "value", required = true) String value,
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "pId", required = false) Integer pId
    ) {
        boolean flag = dictionaryService.getDictionaryInsert(code, value, type, description, pId);
        return new ResultResponse(200, flag ? "SUCCSS" : "FAIL", flag);
    }

    /**
     * 字典列表
     * @param page
     * @param limit
     * @param type
     * @param pId
     * @return
     */
    @ApiOperation(value="字典列表")
    @GetMapping(value = "/list")
    public ResultResponse handleDicSelect(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "pId", required = false) Integer pId
    ) {
        ModelMap modelMap = new ModelMap();
        int total = dictionaryService.getDictionaryTotal(type);
        List<DictionaryInfo> list = dictionaryService.getDictionaryList(page, limit, type, pId);
        modelMap.put("total", total);
        modelMap.put("data", list);
        return new ResultResponse(200, "SUCCSS", modelMap);
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @ApiOperation(value="删除字典")
    @GetMapping(value = "/delete")
    public ResultResponse handleDicDelete(
            @RequestParam(value = "id", required = true) Integer id
    ) {
        boolean flag = dictionaryService.getDicInfoDel(id);
        return new ResultResponse(200, flag ? "SUCCSSS" : "FAIL", flag);
    }

    /**
     * 更新字典
     * @param id
     * @param code
     * @param value
     * @param type
     * @param description
     * @param pId
     * @return
     */
    @ApiOperation(value="更新字典")
    @PostMapping(value = "/update")
    public ResultResponse handleDictionaryUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "value", required = true) String value,
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "pId", required = false) Integer pId
    ) {
        boolean flag = dictionaryService.getDictionaryInfoUpdate(id, code,value, type, description, pId);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 查询字典详情
     * @param id
     * @return
     */
    @ApiOperation(value="查询字典详情")
    @GetMapping(value="/getById")
    public  ResultResponse handleDictionaryInfobyId(
            @RequestParam(value="id",required = true) Integer id
    ){
        DictionaryInfo dictionaryInfo = dictionaryService.getDictionaryInfoById(id);
        return new ResultResponse(200,"SUCCESS",dictionaryInfo);
    }
}
