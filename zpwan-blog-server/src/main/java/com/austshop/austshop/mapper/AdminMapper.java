package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.AdminInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface AdminMapper {

    @Insert("insert into zpwan_blog_admin(admin_id,admin_name,admin_password,role,avatar_url,create_time,modify_time) values(#{admin_id},#{admin_name},#{admin_password},#{role},#{avatar_url},#{create_time},#{modify_time})")
    int getAdminInsert(@Param("admin_id") String admin_id, @Param("admin_name") String admin_name, @Param("admin_password") String admin_password, @Param("role") Integer role,
                       @Param("avatar_url") String avatar_url, @Param("create_time") Date create_time, @Param("modify_time") Date modify_time);


    @Select("select admin_id,admin_name,role,avatar_url,create_time, modify_time from zpwan_blog_admin order by create_time desc  limit #{skip},#{limit}")
    List<AdminInfo> getAdminList(@Param("skip") Integer skip, @Param("limit") Integer limit);

    @Update("update zpwan_blog_admin set admin_name = #{admin_name},admin_password= #{admin_password} ,avatar_url = #{avatar_url},modify_time = #{modify_time} where admin_id = #{admin_id}")
    int getAdminInfoUpdate(@Param("admin_id") String admin_id, @Param("admin_name") String admin_name, @Param("admin_password") String admin_password, @Param("avatar_url") String avatar_url, @Param("modify_time") Date modify_time);

    @Select("select admin_id,admin_name,admin_password,role,avatar_url,create_time,modify_time from zpwan_blog_admin where admin_id =#{admin_id}")
    AdminInfo getAdminInfoById(@Param("admin_id") String admin_id);

    @Select("select admin_password from zpwan_blog_admin where admin_name = #{admin_name}")
    AdminInfo getAdminInfoByAdminName(@Param("admin_name") String admin_name);

    @Select("select count(admin_id) from zpwan_blog_admin")
    int getAdminTotal();

    @Delete("delete from zpwan_blog_admin where admin_id = #{admin_id}")
    int getAdminDelete(@Param("admin_id") String admin_id);
}
