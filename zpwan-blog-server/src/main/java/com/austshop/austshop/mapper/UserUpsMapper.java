package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.UserUpsInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface UserUpsMapper {
    @Insert("insert into zpwan_blog_userups(article_id,user_id,create_time,modify_time) values(#{article_id},#{user_id},#{create_time},#{modify_time})")
    int insertUserUps(@Param("article_id") String article_id, @Param("user_id") String user_id, @Param("create_time") Date create_time, @Param("modify_time") Date modify_time);

    @Delete("delete from zpwan_blog_userups where article_id = #{article_id} and user_id = #{user_id}")
    int deleteUserUps(@Param("article_id") String article_id, @Param("user_id") String user_id);

    @Select("select * from zpwan_blog_userups where article_id = #{article_id} and user_id = #{user_id}")
    UserUpsInfo getUserUpsInfo(@Param("article_id") String article_id, @Param("user_id") String user_id);
}
