package com.austshop.austshop.mapper.sqlProvider;

import com.austshop.austshop.entity.FileInfo;
import org.apache.ibatis.jdbc.SQL;

public class FileSqlProvider {

    public String insertFileSelective(FileInfo fileInfo){
        SQL sql = new SQL();

        sql.INSERT_INTO("zpwan_blog_file");


        if (fileInfo.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }

        if (fileInfo.getFileName() != null) {
            sql.VALUES("fileName", "#{fileName,jdbcType=VARCHAR}");
        }

        if (fileInfo.getMd5() != null) {
            sql.VALUES("md5", "#{md5,jdbcType=VARCHAR}");
        }

        if (fileInfo.getLength() != null) {
            sql.VALUES("length", "#{length,jdbcType=VARCHAR}");
        }

        if (fileInfo.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=VARCHAR}");
        }

        if (fileInfo.getCreate_time() != null) {
            sql.VALUES("create_time", "#{create_time,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

}


