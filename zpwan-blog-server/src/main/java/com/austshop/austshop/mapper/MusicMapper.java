package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.MusicInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MusicMapper {

    // 新增
    @Insert("insert into zpwan_blog_music(id,src,title,author,pic) values(#{id},#{src},#{title},#{author},#{pic})")
    int handleMusicCreate(@Param("id") String id, @Param("src") String src, @Param("title") String title, @Param("pic") String pic, @Param("author") String author);

    //编辑
    @Update("update zpwan_blog_music set src=#{src}, title=#{title}, pic=#{pic}, author=#{author} where id = #{id}")
    int handleMusicUpdate(@Param("id") String id, @Param("src") String src, @Param("title") String title, @Param("pic") String pic, @Param("author") String author);

    // 查列表
    @Select({
            "<script>",
            "select",
            "id, title,src, pic, author",
            "from zpwan_blog_music",
            "where 1=1",
            "<if test = 'title != null and title != \"\"'> and title like concat('%',#{title, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'author != null and author != \"\"'> and author like concat('%',#{author, jdbcType=VARCHAR},'%')</if>",
            "limit #{skip},#{limit}",
            "</script>"
    })
    List<MusicInfo> getMusicList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("title") String title, @Param("author") String author);

    // 查总数
    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_music",
            "where 1=1",
            "<if test = 'title != null and title != \"\"'> and title like concat('%',#{title, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'author != null and author != \"\"'> and author like concat('%',#{author, jdbcType=VARCHAR},'%')</if>",
            "</script>"
    })
    int getMusicListCount(@Param("title") String title, @Param("author") String author);

    //删除
    @Delete("delete from zpwan_blog_music where id = #{id}")
    int handleMusicDelete(@Param("id") String id);

    // 查详情
    @Select("select id,title,author,src,pic from zpwan_blog_music where id = #{id}")
    MusicInfo getMusicInfoById(@Param("id") String id);
}
