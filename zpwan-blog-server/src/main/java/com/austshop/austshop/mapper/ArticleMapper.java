package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.ArticleInfo;
import com.austshop.austshop.mapper.sqlProvider.ArticleSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {
    @Select({
            "<script>",
            "select",
            "id, title, description, imgUrl, tags,first_catalogId, first_catalogName, second_catalogId, second_catalogName, ",
            "isDelete,isShelves,isRecommend,isTop,visit_count,like_count,comment_count,create_time, modify_time",
            "from zpwan_blog_article",
            "where 1=1 and isDelete = 0",
            "<if test = 'key != null and key != \"\"'> and ( title like concat('%',#{key, jdbcType=VARCHAR},'%') or content like concat('%',#{key, jdbcType=VARCHAR},'%')or description like concat('%',#{key, jdbcType=VARCHAR},'%'))</if>",
            "<if test = 'title != null and title != \"\"'> and title like concat('%',#{title, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'first_catalogId != null and first_catalogId != \"\"'> and first_catalogId =#{first_catalogId}</if>",
            "<if test = 'second_catalogId != null and second_catalogId != \"\"'> and second_catalogId = #{second_catalogId}</if>",
            "<if test = 'isShelves != null'> and isShelves = #{isShelves}</if>",
            "<if test = 'isRecommend != null'> and isRecommend = #{isRecommend}</if>",
            "order by create_time desc limit #{skip},#{limit}",
            "</script>"
    })
    List<ArticleInfo> queryArticleList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("key") String key, @Param("title") String title,
                                       @Param("first_catalogId") Integer first_catalogId, @Param("second_catalogId") Integer second_catalogId,
                                       @Param("isShelves") Integer isShelves, @Param("isRecommend") Integer isRecommend);

    @Select({
            "<script>",
            "select",
            "id, title, description, imgUrl, tags,first_catalogId, first_catalogName, second_catalogId, second_catalogName, ",
            "isDelete,isShelves,isRecommend,isTop,visit_count,like_count,comment_count,create_time, modify_time",
            "from zpwan_blog_article",
            "where 1=1 and isDelete = 0 and isShelves = 1",
            "order by visit_count desc ,create_time desc limit 0,9",
            "</script>"
    })
    List<ArticleInfo> queryHotArticleList();

    @Select({
            "<script>",
            "select",
            "id, title, description, imgUrl, tags,first_catalogId, first_catalogName, second_catalogId, second_catalogName, ",
            "isDelete,isShelves,isRecommend,isTop,visit_count,like_count,comment_count,create_time, modify_time",
            "from zpwan_blog_article",
            "where 1=1 and isDelete = 0 and isShelves = 1",
            "<if test = 'second_catalogId != null and second_catalogId != \"\"'> and second_catalogId = #{second_catalogId}</if>",
            "order by isTop desc ,create_time desc  limit #{skip},#{limit}",
            "</script>"
    })
    List<ArticleInfo> queryWebArticleList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("second_catalogId") Integer second_catalogId);

    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_article",
            "where 1=1 and isDelete = 0",
            "<if test = 'key != null and key != \"\"'> and ( title like concat('%',#{key, jdbcType=VARCHAR},'%') or content like concat('%',#{key, jdbcType=VARCHAR},'%')or description like concat('%',#{key, jdbcType=VARCHAR},'%'))</if>",
            "<if test = 'first_catalogId != null and first_catalogId != \"\"'> and first_catalogId =#{first_catalogId}</if>",
            "<if test = 'second_catalogId != null and second_catalogId != \"\"'> and second_catalogId = #{second_catalogId}</if>",
            "<if test = 'title != null and title != \"\"'> and title =#{title}</if>",
            "<if test = 'isShelves != null'> and isShelves =#{isShelves}</if>",
            "<if test = 'isRecommend != null'> and isRecommend =#{isRecommend}</if>",
            "</script>"
    })
    int getArticleListTotal(@Param("key") String key, @Param("title") String title, @Param("first_catalogId") Integer first_catalogId,
                            @Param("second_catalogId") Integer second_catalogId,
                            @Param("isShelves") Integer isShelves, @Param("isRecommend") Integer isRecommend);

    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_article",
            "where 1=1 and isDelete = 0 and isShelves = 1",
            "<if test = 'second_catalogId != null and second_catalogId != \"\"'> and second_catalogId = #{second_catalogId}</if>",
            "order by isTop desc ,create_time desc ",
            "</script>"
    })
    int getWebArticleListTotal(@Param("second_catalogId") Integer second_catalogId);


    @InsertProvider(type = ArticleSqlProvider.class, method = "insertSelective")
    int insertSelective(ArticleInfo articleInfo);

    @UpdateProvider(type = ArticleSqlProvider.class, method = "updateSelective")
    int updateSelective(ArticleInfo articleInfo);

    @Select("select id,title,content,md,imgUrl,description,author,tags,quotes,first_catalogId,first_catalogName,second_catalogId,second_catalogName,isDelete," +
            "isShelves,isRecommend,isTop,visit_count,like_count,comment_count,create_time,modify_time from zpwan_blog_article where id = #{ id }")
    ArticleInfo getArticleInfoById(@Param("id") String id);

    @Update("update zpwan_blog_article set isTop = #{isTop} where id = #{id}")
    int getArticleInfoTop(@Param("id") String id, @Param("isTop") Integer isTop);

    @Update("update zpwan_blog_article set isShelves = #{isShelves} where id = #{id}")
    int getArticleInfoIsShelves(@Param("id") String id, @Param("isShelves") Integer isShelves);

    @Update("update zpwan_blog_article set isRecommend = #{isRecommend} where id = #{id}")
    int getArticleInfoRecommend(@Param("id") String id, @Param("isRecommend") Integer isRecommend);

    @Update("update zpwan_blog_article set isDelete = 1 where id = #{id}")
    int getArticleInfoDelete(@Param("id") String id);

}
