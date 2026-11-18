package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.TagInfo;
import com.austshop.austshop.mapper.sqlProvider.TagSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper {

    @InsertProvider(type = TagSqlProvider.class, method = "tagInsertSelective")
    int tagInsertSelective(TagInfo tagInfo);

    @Select("select id,code,value,create_time,modify_time from zpwan_blog_tag order by create_time desc limit #{skip},#{limit}")
    List<TagInfo> getTagList(@Param("skip") Integer skip, @Param("limit") Integer limit);

    @Select("select count(id) from zpwan_blog_tag")
    int gettotal();

    @Delete("delete from zpwan_blog_tag where id= #{id}")
    int getTagDelete(@Param("id") Integer id);
}
