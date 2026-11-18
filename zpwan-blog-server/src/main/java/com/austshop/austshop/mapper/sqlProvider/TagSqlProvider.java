package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.TagInfo;
import org.apache.ibatis.jdbc.SQL;

public class TagSqlProvider {
    public String tagInsertSelective(TagInfo tagInfo){
        SQL sql = new SQL();

        sql.INSERT_INTO("zpwan_blog_tag");

        if(tagInfo.getCode() != null){
            sql.VALUES("code","#{code,jdbcType=VARCHAR}");
        }
        if(tagInfo.getValue() != null){
            sql.VALUES("value","#{value,jdbcType=VARCHAR}");
        }
        if(tagInfo.getCreate_time() != null){
            sql.VALUES("create_time","#{create_time,jdbcType=TIMESTAMP}");
        }
        if(tagInfo.getModify_time() != null){
            sql.VALUES("modify_time","#{modify_time,jdbcType=TIMESTAMP}");
        }
        return sql.toString();
    }
}
