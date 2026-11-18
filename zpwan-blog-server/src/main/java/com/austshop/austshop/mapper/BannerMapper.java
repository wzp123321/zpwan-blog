package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.BannerInfo;
import com.austshop.austshop.mapper.sqlProvider.BannerSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BannerMapper {

    @InsertProvider(type=BannerSqlProvider.class,method="insertSelective")
    int insertSelective(BannerInfo bannerInfo);

    @UpdateProvider(type=BannerSqlProvider.class,method="updatetSelective")
    int updatetSelective(BannerInfo bannerInfo);

    @Select({
            "<script>",
            "select",
            "id, title, url, imgUrl,isShelves,create_time,modify_time",
            "from zpwan_blog_banner",
            "where 1=1",
            "<if test = 'isShelves != null'> and isShelves = #{isShelves,jdbcType=BIT}</if>",
            "order by create_time desc",
            "</script>"
    })
    List<BannerInfo> getBannerList(@Param("isShelves") Integer isShelves);

    @Select("select id,title,url,imgUrl,isShelves,create_time,modify_time from zpwan_blog_banner where id =#{id}")
    BannerInfo getBannerInfoById(@Param("id") Integer id);

}
