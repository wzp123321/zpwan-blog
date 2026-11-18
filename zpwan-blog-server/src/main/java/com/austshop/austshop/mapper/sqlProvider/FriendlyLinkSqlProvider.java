package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.FriendlyLinkInfo;
import org.apache.ibatis.jdbc.SQL;

public class FriendlyLinkSqlProvider {
    public String updateSelective(FriendlyLinkInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_friendlylink");

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getIco() != null) {
            sql.SET("ico = #{ico,jdbcType=VARCHAR}");
        }

        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=VARCHAR}");
        }

        if (record.getIsShelves() != null) {
            sql.SET("isShelves = #{isShelves,jdbcType=VARCHAR}");
        }

        if (record.getModify_time() != null) {
            sql.SET("modify_time = #{modify_time,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        return sql.toString();
    }
}
