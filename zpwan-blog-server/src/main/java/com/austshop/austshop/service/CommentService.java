package com.austshop.austshop.service;

import com.alibaba.fastjson.JSONObject;
import com.austshop.austshop.entity.ArticleInfo;
import com.austshop.austshop.entity.CommentInfo;
import com.austshop.austshop.entity.UserInfo;
import com.austshop.austshop.mapper.ArticleMapper;
import com.austshop.austshop.mapper.CommentMapper;
import com.austshop.austshop.service.impl.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value="commentService")
public class CommentService implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 新增评论
     * @param article_id
     * @param author
     * @param content
     * @param parent_id
     * @param is_root
     * @param reply_userInfo
     * @return
     */
    public boolean getCommentInsert(String article_id, String author, String content, String parent_id, boolean is_root, String reply_userInfo) {
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setId(UUID.randomUUID().toString().toLowerCase());
        commentInfo.setArticle_id(article_id);
        commentInfo.setAuthor(author);
        commentInfo.setContent(content);
        commentInfo.setIs_delete(0);
        commentInfo.setUps(0);
        commentInfo.setDowns(0);
        commentInfo.setParent_id(parent_id);
        commentInfo.setIs_root(is_root+"");
        commentInfo.setReply_userInfo(reply_userInfo);
        commentInfo.setCreate_time(new Date());

        /**
         * 修改文章评论数
         */
        ArticleInfo articleInfo =articleMapper.getArticleInfoById(article_id);
        int comment_count = articleInfo.getComment_count();
        articleInfo.setComment_count(comment_count+1);
        articleMapper.updateSelective(articleInfo);
        return commentMapper.insertSelective(commentInfo) > 0;
    }

    /**
     * 请求详情
     * @param id
     * @return
     */
    public CommentInfo getCommentInfoById(String id){
        CommentInfo commentInfo = commentMapper.getCommentInfoById(id);
        commentInfo.setAuthor(JSONObject.parseObject(String.valueOf(commentInfo.getAuthor()),UserInfo.class));
        commentInfo.setReply_userInfo(JSONObject.parseObject(String.valueOf(commentInfo.getReply_userInfo()),UserInfo.class));
        return commentInfo;
    }

    /**
     * 请求列表
     * @param page
     * @param limit
     * @param article_id
     * @return
     */
    public List<CommentInfo> getCommentList(Integer page,Integer limit,String article_id){
        int skip = page>0 ? (page-1)*limit:0;
        List<CommentInfo> list = commentMapper.getCommentList(skip,limit,article_id);
        for (int i=0;i<list.size();i++){
            list.get(i).setAuthor(JSONObject.parseObject(String.valueOf(list.get(i).getAuthor()),UserInfo.class));
            list.get(i).setReply_userInfo(JSONObject.parseObject(String.valueOf(list.get(i).getReply_userInfo()),UserInfo.class));
        }
        return  list;
    }

    /**
     * 获取总数
     * @param article_id
     * @return
     */
    public int getCommentTotal(String article_id){
        return commentMapper.getCommentTotal(article_id);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    public boolean handleCommentDelete(String id) {
        return commentMapper.getCommentDelete(id) > 0;
    }
}
