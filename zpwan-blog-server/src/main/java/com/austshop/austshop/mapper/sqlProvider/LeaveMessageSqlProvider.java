package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.LeaveMessageInfo;
import org.apache.ibatis.jdbc.SQL;

public class LeaveMessageSqlProvider {
    public String insertSelective(LeaveMessageInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zpwan_blog_leavemessage");

        if (record.getLeave_message_id() != null) {
            sql.VALUES("leave_message_id", "#{leave_message_id,jdbcType=VARCHAR}");
        }

        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }

        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }

        if (record.getInit_date() != null) {
            sql.VALUES("init_date", "#{init_date,jdbcType=VARCHAR}");
        }

        if (record.getCreate_time() != null) {
            sql.VALUES("create_time", "#{create_time,jdbcType=TIMESTAMP}");
        }

        if (record.getCreate_time() != null) {
            sql.VALUES("modify_time", "#{modify_time,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }
}
