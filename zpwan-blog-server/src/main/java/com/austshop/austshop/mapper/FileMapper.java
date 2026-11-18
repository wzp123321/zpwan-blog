package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.FileInfo;
import com.austshop.austshop.mapper.sqlProvider.FileSqlProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FileMapper {

    @InsertProvider(type=FileSqlProvider.class,method="insertFileSelective")
    int insertFileSelective(FileInfo fileInfo);

    @Select("select * from zpwan_blog_file where md5 = #{md5}")
    FileInfo getFileInfoByMD5(@Param(value="md5") String md5);
}
