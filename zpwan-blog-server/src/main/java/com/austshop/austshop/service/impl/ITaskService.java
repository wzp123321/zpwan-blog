package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.TaskInfo;

import java.lang.reflect.Array;
import java.util.List;

public interface ITaskService {
    public boolean handleTaskInsert(String title, Integer priority, String endline);

    public boolean handleTaskUpdate(String task_id, String title, Integer priority, Integer is_complete, String endline);

    public List<TaskInfo> handleTaskListSelect(Integer page, Integer limit, Integer is_complete, Integer priority, String endline);

    public int handleTaskTotal(Integer is_complete,Integer priority, String endline);

    public boolean handleTaskBathComplete(String task_ids);

    public TaskInfo getTaskInfoById(String task_id);

    public boolean getTaskDelete(String task_id);
}
