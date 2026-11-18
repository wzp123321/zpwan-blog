package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.DictionaryInfo;
import com.austshop.austshop.mapper.sqlProvider.DictionarySqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DicMapper {

    @InsertProvider(type = DictionarySqlProvider.class, method = "insertSelective")
    int insertSelective(DictionaryInfo dictionaryInfo);

    @Select({
            "<script>",
            "select id , code,value,type,description,pId,pCode,pValue,create_time,modify_time",
            "from zpwan_blog_dictionary",
            "where 1=1",
            "<if test = 'type != null and type != \"\"'> and type =#{type}</if>",
            "<if test = 'pId != null and pId != \"\"'> and pId =#{pId}</if>",
            "order by create_time desc limit #{skip},#{limit}",
            "</script>"
    })
    List<DictionaryInfo> getDictionaryList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("type") Integer type, @Param("pId") Integer pId);

    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_dictionary",
            "where 1=1",
            "<if test = 'type != null and type != \"\"'> and type =#{type}</if>",
            "</script>"
    })
    int getDicTotal(@Param("type") Integer type);

    @Select("select id , code,value,type,description,pId,pCode,pValue,create_time,modify_time from zpwan_blog_dictionary where id = #{ id }")
    DictionaryInfo getDictionaryInfoById(@Param("id") Integer id );

    @Delete("delete from zpwan_blog_dictionary where id = #{ id }")
    int getDictionaryInfoDelete(@Param("id") Integer id);

    @UpdateProvider(type = DictionarySqlProvider.class, method = "updatetSelective")
    int updatetSelective(DictionaryInfo dictionaryInfo);
}
