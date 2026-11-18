package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.CommentInfo;
import com.austshop.austshop.entity.UserInfo;

import java.util.List;

public interface ICommentService {

    boolean getCommentInsert(String article_id,String author,String content,String parent_id,boolean is_root,String reply_userInfo);

    CommentInfo getCommentInfoById(String id);

    List<CommentInfo> getCommentList(Integer page,Integer limit,String article_id);

    int getCommentTotal(String article_id);

    boolean handleCommentDelete(String id);
}
