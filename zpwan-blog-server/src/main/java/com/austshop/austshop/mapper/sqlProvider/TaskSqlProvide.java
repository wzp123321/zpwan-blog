package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.TaskInfo;
import org.apache.ibatis.jdbc.SQL;

public class TaskSqlProvide {
    public String updateTaskInfo(TaskInfo taskInfo) {
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_tasks");
        if (taskInfo.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        if (taskInfo.getIs_complete() != null) {
            sql.SET("is_complete = #{is_complete,jdbcType=VARCHAR}");
        }
        if (taskInfo.getPriority() != null) {
            sql.SET("priority = #{priority,jdbcType=VARCHAR}");
        }
        if (taskInfo.getEndline() != null) {
            sql.SET("endline = #{endline,jdbcType=VARCHAR}");
        }
        if (taskInfo.getModify_time() != null) {
            sql.SET("modify_time = #{modify_time,jdbcType=TIMESTAMP}");
        }
        sql.WHERE("task_id= #{task_id,jdbcType=VARCHAR}");
        return sql.toString();
    }
}
