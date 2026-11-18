package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.LeaveMessageInfo;
import com.austshop.austshop.mapper.sqlProvider.LeaveMessageSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LeaveMessageMapper {

    @InsertProvider(type = LeaveMessageSqlProvider.class, method = "insertSelective")
    int getLeaveMessageInsert(LeaveMessageInfo leaveMessageInfo);

    @Update("update zpwan_blog_leavemessage set is_shelves=#{is_shelves} where leave_message_id = #{leave_message_id}")
    int getLeaveMessageShelves(@Param("leave_message_id")String leave_message_id,@Param("is_shelves")int is_shelves);

    @Update("update zpwan_blog_leavemessage set is_good=#{is_good} where leave_message_id = #{leave_message_id}")
    int getLeaveMessageGood(@Param("leave_message_id")String leave_message_id,@Param("is_good")int is_good);


    @Update("update zpwan_blog_leavemessage set is_delete=1 where leave_message_id = #{leave_message_id}")
    int getLeaveMessageDelete(@Param("leave_message_id") String leave_message_id);

    @Select({
            "<script>",
            "select",
            "leave_message_id, author, content,is_delete,is_shelves,is_good,init_date, ",
            "create_time,modify_time",
            "from zpwan_blog_leavemessage",
            "where 1=1 and is_delete = 0",
            "<if test = 'is_shelves != null'> and is_shelves = #{is_shelves}</if>",
            "<if test = 'is_good != null'> and is_good = #{is_good}</if>",
            "order by create_time desc limit #{skip},#{limit}",
            "</script>"
    })
    List<LeaveMessageInfo> getLeaveMessageList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("is_shelves") Integer is_shelves, @Param("is_good") Integer is_good);

    @Select({
            "<script>",
            "select",
            "count(leave_message_id)",
            "from zpwan_blog_leavemessage",
            "where 1=1 and is_delete = 0 ",
            "<if test = 'is_shelves != null'> and is_shelves = #{is_shelves}</if>",
            "<if test = 'is_good != null'> and is_good = #{is_good}</if>",
            "</script>"
    })
    int getLeaveMessageTotal(@Param("is_shelves") Integer is_shelves, @Param("is_good") Integer is_good);
}
