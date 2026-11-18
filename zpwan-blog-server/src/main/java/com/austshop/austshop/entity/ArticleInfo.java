package com.austshop.austshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 */
@Data
public class ArticleInfo implements Serializable {
    private static final long serialVersionUID = 6616022520443454507L;
    // id
    private String id;
    // 标题
    private String title;
    // 内容 代码 --用于前台显示
    private String content;
    // md文件 用于后台编辑时回显
    private String md;
    // 封面
    private String imgUrl;
    // 附带名言警句
    private String quotes;
    // 描述
    private String description;
    // 作者
    private String author;
    // 标签
    private String tags;
    // 所属一级目录id
    private  Integer first_catalogId;
    // 所属一级目录名
    private String first_catalogName;
    // 所属二级目录id
    private  Integer second_catalogId;
    // 所属二级目录名
    private String second_catalogName;
    // 是否被删除
    private Integer isDelete;
    // 是否上架
    private Integer isShelves;
    // 是否推荐
    private Integer isRecommend;
    // 是否置顶
    private Integer isTop;
    // 访问量
    private Integer visit_count;
    // 点赞量
    private Integer like_count;
    // 评论量
    private Integer comment_count;
    // 创建时间
    private Date create_time;
    // 修改时间
    private Date modify_time;

}
