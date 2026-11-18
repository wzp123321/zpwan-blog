package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.BannerInfo;
import org.apache.ibatis.jdbc.SQL;

public class BannerSqlProvider {
    public String insertSelective(BannerInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zpwan_blog_banner");

        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }

        if (record.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=VARCHAR}");
        }

        if (record.getImgUrl() != null) {
            sql.VALUES("imgUrl", "#{imgUrl,jdbcType=VARCHAR}");
        }

        if (record.getCreate_time() != null) {
            sql.VALUES("create_time", "#{create_time,jdbcType=TIMESTAMP}");
        }

        if (record.getModify_time() != null) {
            sql.VALUES("modify_time", "#{modify_time,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updatetSelective(BannerInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_banner");

        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=INTEGER}");
        }

        if (record.getImgUrl() != null) {
            sql.SET("imgUrl = #{imgUrl,jdbcType=VARCHAR}");
        }

        if (record.getIsShelves() != null) {
            sql.SET("isShelves = #{isShelves,jdbcType=VARCHAR}");
        }

        if (record.getModify_time() != null) {
            sql.SET("modify_time = #{modify_time,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id=#{id,jdbcType=INTEGER}");

        return sql.toString();
    }
}
