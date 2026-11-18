package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.NoticeInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface NoticeMapper {

    @Insert("insert into zpwan_blog_notices(content,isEmergency) values(#{content},#{isEmergency})")
    int handleNoticeCreate(@Param("content") String content,@Param("isEmergency") int isEmergency);

    @Update("update zpwan_blog_notices set content = #{content} , isEmergency = #{isEmergency} , modify_time = #{modify_time} where id = #{id}")
    int handleNoticeUpdate(@Param("id") int id, @Param("content") String content, @Param("isEmergency") int isEmergency, @Param("modify_time") Date modify_time);

    @Select("select * from zpwan_blog_notices")
    List<NoticeInfo> handleNoticeList();

    @Delete("delete from zpwan_blog_notices where id = #{id}")
    int handleNoticeDelete(@Param("id") int id);

    @Select("select * from zpwan_blog_notices where id= #{id}")
    NoticeInfo getNoticeInfoById(@Param("id") int id);
}
