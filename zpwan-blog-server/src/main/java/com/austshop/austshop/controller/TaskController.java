package com.austshop.austshop.controller;

import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.entity.TaskInfo;
import com.austshop.austshop.service.TaskService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "任务模块")
@RestController
@RequestMapping("/blogManage/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 新增任务
     * @param title
     * @param priority
     * @param endline
     * @return
     */
    @ApiOperation(value="新增任务")
    @PostMapping(value = "/add")
    public ResultResponse handleTaskInsert(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "priority", required = true) Integer priority,
            @RequestParam(value = "endline", required = true) String endline
    ) {
        boolean flag = taskService.handleTaskInsert(title, priority, endline);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 更新任务
     * @param task_id
     * @param title
     * @param priority
     * @param is_complete
     * @param endline
     * @return
     */
    @ApiOperation(value="更新任务")
    @PostMapping(value = "/update")
    public ResultResponse handleTaskUpdate(
            @RequestParam(value = "task_id", required = false) String task_id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "priority", required = false) Integer priority,
            @RequestParam(value = "is_complete", required = false) Integer is_complete,
            @RequestParam(value = "endline", required = true) String endline
    ) {
        boolean flag = taskService.handleTaskUpdate(task_id, title, priority, is_complete, endline);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 任务列表
     * @param page
     * @param limit
     * @param is_complete
     * @param priority
     * @param endline
     * @return
     */
    @ApiOperation(value="任务列表")
    @GetMapping(value = "/list")
    public ResultResponse handleTaskSelectList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "is_complete", required = false) Integer is_complete,
            @RequestParam(value = "priority", required = false) Integer priority,
            @RequestParam(value = "endline", required = false) String endline
    ) {
        List<TaskInfo> taskInfos = taskService.handleTaskListSelect(page, limit, is_complete, priority, endline);
        int total = taskService.handleTaskTotal(is_complete, priority, endline);
        ModelMap modelMap = new ModelMap();
        modelMap.put("total", total);
        modelMap.put("data", taskInfos);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 批量完成任务
     * @param task_ids
     * @return
     */
    @ApiOperation(value="批量完成任务")
    @GetMapping(value = "/bathComplete")
    public ResultResponse handleTaskBathComplete(
            @RequestParam(value = "task_ids", required = true) String task_ids
    ) {
        boolean flag = taskService.handleTaskBathComplete(task_ids);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 获取任务详情
     * @param task_id
     * @return
     */
    @ApiOperation(value="获取任务详情")
    @GetMapping(value = "/getById")
    public ResultResponse handleTaskInfoById(
            @RequestParam(value = "task_id", required = true) String task_id
    ) {
        TaskInfo taskInfo = taskService.getTaskInfoById(task_id);
        return new ResultResponse(200, "SUCCESS", taskInfo);
    }

    /**
     * 任务删除
     * @param task_id
     * @return
     */
    @ApiOperation(value="任务删除")
    @GetMapping(value = "/delete")
    public ResultResponse handleTaskDeleteById(
            @RequestParam(value = "task_id", required = true) String task_id
    ) {
        boolean flag = taskService.getTaskDelete(task_id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
