package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.PictureInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PictureMapper {

    @Insert("insert into zpwan_blog_picture(picture_id,picture_url,createDate,init_date) values(#{picture_id},#{picture_url},#{createDate},#{init_date})")
    int handlePictureInsert(@Param("picture_id") String picture_id, @Param("picture_url") String picture_url, @Param("createDate") Long createDate, @Param("init_date") String init_date);

    @Select({
            "<script>",
            "select *",
            "from zpwan_blog_picture where createDate &gt; #{startDate} and createDate  &lt;  #{endDate}",
            "order by create_time desc limit #{skip}, #{limit}",
            "</script>"
    })
    List<PictureInfo> handlePictureList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("startDate") Long startDate, @Param("endDate") Long endDate);

    @Select({
            "<script>",
            "select count(picture_id)",
            "from zpwan_blog_picture where createDate &gt; #{startDate} and createDate &lt; #{endDate}",
            "</script>"
    })
    int handlePictureListTotal(@Param("startDate") Long startDate, @Param("endDate") Long endDate);

    @Delete("delete from zpwan_blog_picture where picture_id = #{picture_id}")
    int handlePictureDelete(@Param("picture_id") String picture_id);
}
