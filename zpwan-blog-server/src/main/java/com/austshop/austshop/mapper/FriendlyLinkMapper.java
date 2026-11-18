package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.FriendlyLinkInfo;
import com.austshop.austshop.mapper.sqlProvider.FriendlyLinkSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface FriendlyLinkMapper {

    @Insert({"insert into zpwan_blog_friendlylink(name,ico,url,isShelves,create_time,modify_time) values(#{name},#{ico},#{url},#{isShelves},#{create_time},#{modify_time})"})
    int insertFriendltLink(@Param("name") String name, @Param("ico") String ico, @Param("url") String url,@Param("isShelves") Integer isShelves, @Param("create_time") Date create_time,@Param("modify_time")  Date modify_time);

    @Select({
            "<script>",
            "select",
            "id, name, ico, url, isShelves,create_time, modify_time",
            "from zpwan_blog_friendlylink",
            "where 1=1",
            "<if test = 'name != null and name != \"\"'> and name like concat('%',#{name, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'isShelves != null'> and isShelves = #{isShelves}</if>",
            "order by create_time desc limit #{skip},#{limit}",
            "</script>"
    })
    List<FriendlyLinkInfo> getFriendlyLinkList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("name") String name,@Param("isShelves")  Integer isShelves);

    @UpdateProvider(type = FriendlyLinkSqlProvider.class, method = "updateSelective")
    int updateSelective(FriendlyLinkInfo friendlyLinkInfo);

    @Select("select * from zpwan_blog_friendlylink where id= #{id}")
    FriendlyLinkInfo getFriendlyLinkInfoById(@Param("id") Integer id);

    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_friendlylink",
            "where 1=1",
            "<if test = 'name != null and name != \"\"'> and name like concat('%',#{name, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'isShelves != null and isShelves != \"\"'> and isShelves = #{isShelves}</if>",
            "</script>"
    })
    int getFriendlyLinkTotal(@Param("name") String name, @Param("isShelves") Integer isShelves);
}
