package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.ArticleInfo;
import org.apache.ibatis.jdbc.SQL;

public class ArticleSqlProvider {
    public String insertSelective(ArticleInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zpwan_blog_article");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }

        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }

        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }

        if (record.getMd() != null) {
            sql.VALUES("md", "#{md,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }

        if (record.getQuotes() != null) {
            sql.VALUES("quotes", "#{quotes,jdbcType=VARCHAR}");
        }

        if (record.getImgUrl() != null) {
            sql.VALUES("imgUrl", "#{imgUrl,jdbcType=VARCHAR}");
        }

        if (record.getTags() != null) {
            sql.VALUES("tags", "#{tags,jdbcType=VARCHAR}");
        }

        if (record.getFirst_catalogId() != null) {
            sql.VALUES("first_catalogId", "#{first_catalogId,jdbcType=VARCHAR}");
        }

        if (record.getFirst_catalogName() != null) {
            sql.VALUES("first_catalogName", "#{first_catalogName,jdbcType=VARCHAR}");
        }

        if (record.getSecond_catalogId() != null) {
            sql.VALUES("second_catalogId", "#{second_catalogId,jdbcType=VARCHAR}");
        }

        if (record.getSecond_catalogName() != null) {
            sql.VALUES("second_catalogName", "#{second_catalogName,jdbcType=VARCHAR}");
        }

        if (record.getCreate_time() != null) {
            sql.VALUES("create_time", "#{create_time,jdbcType=TIMESTAMP}");
        }

        if (record.getModify_time() != null) {
            sql.VALUES("modify_time", "#{modify_time,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateSelective(ArticleInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_article");

        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }

        if (record.getMd() != null) {
            sql.SET("md = #{md,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }

        if (record.getImgUrl() != null) {
            sql.SET("imgUrl = #{imgUrl,jdbcType=VARCHAR}");
        }

        if (record.getTags() != null) {
            sql.SET("tags = #{tags,jdbcType=VARCHAR}");
        }

        if (record.getQuotes() != null) {
            sql.SET("quotes = #{quotes,jdbcType=VARCHAR}");
        }

        if (record.getFirst_catalogId() != null) {
            sql.SET("first_catalogId = #{first_catalogId,jdbcType=VARCHAR}");
        }

        if (record.getFirst_catalogName() != null) {
            sql.SET("first_catalogName = #{first_catalogName,jdbcType=VARCHAR}");
        }

        if (record.getSecond_catalogId() != null) {
            sql.SET("second_catalogId = #{second_catalogId,jdbcType=VARCHAR}");
        }

        if (record.getSecond_catalogName() != null) {
            sql.SET("second_catalogName = #{second_catalogName,jdbcType=TIMESTAMP}");
        }

        if (record.getVisit_count() != null) {
            sql.SET("visit_count = #{visit_count,jdbcType=VARCHAR}");
        }

        if (record.getLike_count() != null) {
            sql.SET("like_count = #{like_count,jdbcType=VARCHAR}");
        }

        if (record.getComment_count() != null) {
            sql.SET("comment_count = #{comment_count,jdbcType=TIMESTAMP}");
        }

        if (record.getModify_time() != null) {
            sql.SET("modify_time = #{modify_time,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        return sql.toString();
    }


}
