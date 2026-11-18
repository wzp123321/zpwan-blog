package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.DictionaryInfo;
import org.apache.ibatis.jdbc.SQL;

public class DictionarySqlProvider {
    public String insertSelective(DictionaryInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zpwan_blog_dictionary");

        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }

        if (record.getValue() != null) {
            sql.VALUES("value", "#{value,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }

        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        if (record.getPId() != null) {
            sql.VALUES("pId", "#{pId,jdbcType=INTEGER}");
        }

        if (record.getPCode() != null) {
            sql.VALUES("pCode", "#{pCode,jdbcType=VARCHAR}");
        }

        if (record.getPValue() != null) {
            sql.VALUES("pValue", "#{pValue,jdbcType=VARCHAR}");
        }

        if (record.getCreate_time() != null) {
            sql.VALUES("create_time", "#{create_time,jdbcType=TIMESTAMP}");
        }

        if (record.getModify_time() != null) {
            sql.VALUES("modify_time", "#{modify_time,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updatetSelective(DictionaryInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_dictionary");

        if (record.getValue() != null) {
            sql.SET("value = #{value,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }

        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }

        if (record.getPId() != null) {
            sql.SET("pId = #{pId,jdbcType=INTEGER}");
        }

        if (record.getPCode() != null) {
            sql.SET("pCode = #{pCode,jdbcType=VARCHAR}");
        }

        if (record.getPValue() != null) {
            sql.SET("pValue = #{pValue,jdbcType=VARCHAR}");
        }

        if (record.getModify_time() != null) {
            sql.SET("modify_time = #{modify_time,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id=#{id,jdbcType=INTEGER}");

        return sql.toString();
    }
}
