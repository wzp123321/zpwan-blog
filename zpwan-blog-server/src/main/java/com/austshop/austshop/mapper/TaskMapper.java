package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.TaskInfo;
import com.austshop.austshop.mapper.sqlProvider.TaskSqlProvide;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TaskMapper {
    @Insert("insert into zpwan_blog_tasks(task_id,title,priority,endline) values(#{task_id},#{title},#{priority},#{endline})")
    int handleTaskCreate(@Param("task_id") String task_id, @Param("title") String title, @Param("priority") Integer priority, @Param("endline") String endline);

    @UpdateProvider(type = TaskSqlProvide.class, method = "updateTaskInfo")
    int handleTaskEdit(TaskInfo taskInfo);

    @Select({
            "<script>",
            "select task_id,title,priority,is_complete,endline,create_time,modify_time",
            "from zpwan_blog_tasks",
            "where 1=1 ",
            "<if test = 'is_complete != null'> and is_complete = #{is_complete,jdbcType=BIT}</if>",
            "<if test = 'priority != null'> and priority = #{priority,jdbcType=BIT}</if>",
            "<if test = 'endline != null and endline != \"\"'> and endline = #{endline}</if>",
            "order by create_time desc limit #{skip},#{limit}",
            "</script>"
    })
    List<TaskInfo> handleTaskSelect(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("is_complete") Integer is_complete, @Param("priority") Integer priority, @Param("endline") String endline);

    @Select({
            "<script>",
            "select count(task_id) from zpwan_blog_tasks where 1=1 ",
            "<if test = 'is_complete != null'> and is_complete = #{is_complete,jdbcType=BIT}</if>",
            "<if test = 'priority != null'> and priority = #{priority,jdbcType=BIT}</if>",
            "<if test = 'endline != null and endline != \"\"'> and endline =#{endline}</if>",
            "</script>"
    })
    int handleTaskSelectTotal(@Param("priority") Integer priority, @Param("is_complete") Integer is_complete, @Param("endline") String endline);

    @Select("select * from zpwan_blog_tasks where task_id = #{task_id}")
    TaskInfo handleTaskInfoById(@Param("task_id") String task_id);

    @Delete("delete from zpwan_blog_tasks where task_id = #{task_id}")
    int handleTaskDelete(@Param("task_id") String task_id);
}
