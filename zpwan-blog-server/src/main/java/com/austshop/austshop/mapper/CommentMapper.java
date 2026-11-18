package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.CommentInfo;
import com.austshop.austshop.entity.UserInfo;
import com.austshop.austshop.mapper.sqlProvider.CommentSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {

    @InsertProvider(type=CommentSqlProvider.class, method="insertSelective")
    int insertSelective(CommentInfo commentInfo);

    @Select("select * from zpwan_blog_comment where id = #{id}")
    CommentInfo getCommentInfoById(@Param("id") String id);

    @Select({
            "<script>",
            "select",
            "id, author, content, is_delete, article_id,ups, downs, parent_id, is_root, ",
            "reply_userInfo,create_time",
            "from zpwan_blog_comment",
            "where 1=1 and is_delete = 0",
            "<if test = 'article_id != null and article_id != \"\"'> and article_id =#{article_id}</if>",
            "order by create_time asc limit #{skip},#{limit}",
            "</script>"
    })
    List<CommentInfo> getCommentList(@Param("skip") Integer skip,@Param("limit") Integer limit,@Param("article_id") String article_id);

    @Select({
            "<script>",
            "select",
            "count(id)",
            "from zpwan_blog_comment",
            "where 1=1 and is_delete = 0 ",
            "<if test = 'article_id != null and article_id != \"\"'> and article_id =#{article_id}</if>",
            "</script>"
    })
    int getCommentTotal(@Param("article_id") String article_id);

    @Update("update zpwan_blog_comment set is_delete= 1 where id = #{id}")
    int getCommentDelete(@Param("id") String id);

}
