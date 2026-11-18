package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.CommentInfo;
import org.apache.ibatis.jdbc.SQL;

public class CommentSqlProvider {

    public String insertSelective(CommentInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zpwan_blog_comment");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }

        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }

        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }

        if (record.getArticle_id() != null) {
            sql.VALUES("article_id", "#{article_id,jdbcType=VARCHAR}");
        }

        if (record.getUps() != null) {
            sql.VALUES("ups", "#{ups,jdbcType=VARCHAR}");
        }

        if (record.getDowns() != null) {
            sql.VALUES("downs", "#{downs,jdbcType=VARCHAR}");
        }

        if (record.getIs_delete() != null) {
            sql.VALUES("is_delete", "#{is_delete,jdbcType=VARCHAR}");
        }

        if (record.getParent_id() != null) {
            sql.VALUES("parent_id", "#{parent_id,jdbcType=VARCHAR}");
        }

        if (record.getIs_root() != null) {
            sql.VALUES("is_root", "#{is_root,jdbcType=VARCHAR}");
        }

        if (record.getReply_userInfo() != null) {
            sql.VALUES("reply_userInfo", "#{reply_userInfo,jdbcType=VARCHAR}");
        }

        if (record.getCreate_time() != null) {
            sql.VALUES("create_time", "#{create_time,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

}
