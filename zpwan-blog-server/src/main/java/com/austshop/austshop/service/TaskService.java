package com.austshop.austshop.service;

import com.austshop.austshop.entity.TaskInfo;
import com.austshop.austshop.mapper.TaskMapper;
import com.austshop.austshop.service.impl.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("taskService")
public class TaskService implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;

    public boolean handleTaskInsert(String title, Integer priority, String endline) {
        return taskMapper.handleTaskCreate(UUID.randomUUID().toString().toLowerCase(), title, priority, endline) > 0;
    }

    public boolean handleTaskUpdate(String task_id, String title, Integer priority, Integer is_complete, String endline) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTask_id(task_id);
        taskInfo.setTitle(title);
        taskInfo.setPriority(priority);
        taskInfo.setIs_complete(is_complete);
        taskInfo.setEndline(endline);
        taskInfo.setModify_time(new Date());
        return taskMapper.handleTaskEdit(taskInfo) > 0;
    }

    public List<TaskInfo> handleTaskListSelect(Integer page, Integer limit, Integer is_complete, Integer priority, String endline) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        List<TaskInfo> taskInfos = taskMapper.handleTaskSelect(skip, limit, is_complete, priority, endline);
        return taskInfos;
    }

    public int handleTaskTotal(Integer is_complete, Integer priority, String endline) {
        return taskMapper.handleTaskSelectTotal(is_complete, priority, endline);
    }

    public boolean handleTaskBathComplete(String task_ids) {
        boolean flag = true;
        TaskInfo taskInfo = new TaskInfo();
        String[] ids = task_ids.split(",");
        for (int i = 0; i < ids.length; i++) {
            taskInfo.setTask_id(ids[i]);
            taskInfo.setIs_complete(1);
            if (taskMapper.handleTaskEdit(taskInfo) == 0) {
                flag = false;
            }
        }
        return flag;
    }

    public TaskInfo getTaskInfoById(String task_id) {
        return taskMapper.handleTaskInfoById(task_id);
    }

    public boolean getTaskDelete(String task_id) {
        return taskMapper.handleTaskDelete(task_id) > 0;
    }
}
