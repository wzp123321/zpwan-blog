package com.austshop.austshop.service;

import com.alibaba.fastjson.JSONObject;
import com.austshop.austshop.Utils.FileUtils;
import com.austshop.austshop.Utils.SendMail;
import com.austshop.austshop.controller.WebSocketController;
import com.austshop.austshop.entity.ArticleInfo;
import com.austshop.austshop.entity.MessageInfo;
import com.austshop.austshop.mapper.ArticleMapper;
import com.austshop.austshop.mapper.DicMapper;
import com.austshop.austshop.service.impl.IArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value = "articleService")
public class ArticleService implements IArticleService {
    private static Logger logger = Logger.getLogger(ArticleService.class);


    @Autowired
    public ArticleMapper articleMapper;

    @Autowired
    public DicMapper dicMapper;

    @Autowired
    private WebSocketController webSocketController;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private SendMail sendMail = new SendMail();

    /**
     * 后台动态查询文章列表  前台搜索  前台推荐  前台近期
     *
     * @param page
     * @param limit
     * @param key
     * @param title
     * @param first_catalogId
     * @param second_catalogId
     * @param isShelves
     * @param isRecommend
     * @return
     */
    public List<ArticleInfo> getArticleList(Integer page, Integer limit, String key, String title, Integer first_catalogId, Integer second_catalogId, Integer isShelves, Integer isRecommend) {
        int skip = page > 0 ? (page - 1) * limit : 0;
        return articleMapper.queryArticleList(skip, limit, key, title, first_catalogId, second_catalogId, isShelves, isRecommend);
    }

    /**
     * web端查询文章列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<ArticleInfo> getWebArticleList(Integer page, Integer limit, Integer second_catalogId) {
        int skip = page > 0 ? (page - 1) * limit : 0;
        List<ArticleInfo> perceList = articleMapper.queryWebArticleList(skip, limit, second_catalogId);
        return perceList;
    }

    /**
     * 获取热门文章
     */
    public List<ArticleInfo> getHotArticleList() {
        return articleMapper.queryHotArticleList();
    }

    /**
     * 新增文章
     *
     * @param title
     * @param content
     * @param md
     * @param description
     * @param imgUrl
     * @param tags
     * @param first_catalogId
     * @param second_catalogId
     * @return
     */
    public boolean getArticleAdd(String title, String content, String md, String description, String imgUrl, String tags, String quotes,
                                 Integer first_catalogId, Integer second_catalogId) {
        String id = UUID.randomUUID().toString().toLowerCase();
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(id);
        articleInfo.setTitle(title);
        articleInfo.setContent(content);
        articleInfo.setMd(md);
        articleInfo.setImgUrl(imgUrl);
        articleInfo.setDescription(description.equals("undefined") ? "" : description);
        articleInfo.setTags(tags);
        articleInfo.setQuotes(quotes);
        articleInfo.setFirst_catalogId(first_catalogId);
        articleInfo.setFirst_catalogName(dicMapper.getDictionaryInfoById(first_catalogId).getValue());
        articleInfo.setSecond_catalogId(second_catalogId);
        articleInfo.setSecond_catalogName(dicMapper.getDictionaryInfoById(second_catalogId).getValue());
        /**
         * 消息通知
         */
        webSocketController.sendInfo(1, title, id);
        sendMail.handleMailSend(javaMailSender, "新文章发布", id, "又有新文章发布啦");
        return articleMapper.insertSelective(articleInfo) > 0;
    }

    /**
     * 更新文章
     *
     * @param id
     * @param title
     * @param content
     * @param md
     * @param description
     * @param imgUrl
     * @param tags
     * @param first_catalogId
     * @param second_catalogId
     * @return
     */
    public boolean getArticleInfoUpdate(String id, String title, String content, String md, String description, String imgUrl,
                                        String tags, String quotes, Integer first_catalogId, Integer second_catalogId) {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(id);
        articleInfo.setTitle(title);
        articleInfo.setContent(content);
        articleInfo.setMd(md);
        articleInfo.setImgUrl(imgUrl);
        articleInfo.setDescription(description);
        articleInfo.setTags(tags);
        articleInfo.setQuotes(quotes);
        articleInfo.setFirst_catalogId(first_catalogId);
        articleInfo.setFirst_catalogName(dicMapper.getDictionaryInfoById(first_catalogId).getValue());
        articleInfo.setSecond_catalogId(second_catalogId);
        articleInfo.setSecond_catalogName(dicMapper.getDictionaryInfoById(second_catalogId).getValue());
        articleInfo.setModify_time(new Date());
        /**
         * 消息通知
         */
        webSocketController.sendInfo(2, title, id);
        sendMail.handleMailSend(javaMailSender, "文章更新", id, "有文章更新啦");
        return articleMapper.updateSelective(articleInfo) > 0;
    }

    /**
     * 查询详细信息
     *
     * @param id
     * @return
     */
    public ArticleInfo getArticleInfoById(String id) {
        return articleMapper.getArticleInfoById(id);
    }

    /**
     * 获取文章列表总数
     *
     * @return
     */
    public int getArticleTotal(String key, String title, Integer first_catalogId, Integer second_catalogId, Integer isShelves, Integer isRecommend) {
        return articleMapper.getArticleListTotal(key, title, first_catalogId, second_catalogId, isShelves, isRecommend);
    }

    /**
     * web端
     *
     * @return
     */
    public int getWebArticleTotal(Integer second_catalogId) {
        return articleMapper.getWebArticleListTotal(second_catalogId);
    }

    /**
     * 文章置顶
     *
     * @param id
     * @param isTop 0:上架  1：下架
     * @return
     */
    public boolean getArticleTop(String id, Integer isTop) {
        int top = isTop == 1 ? 0 : 1;
        return articleMapper.getArticleInfoTop(id, top) > 0;
    }

    /**
     * 文章删除
     *
     * @param id
     * @return
     */
    public boolean getArticleDelete(String id) {
        return articleMapper.getArticleInfoDelete(id) > 0;
    }

    /**
     * 文章上下架
     *
     * @param id
     * @param isShelves 0:上架  1：下架
     * @return
     */
    public boolean getArticleIsShelves(String id, Integer isShelves) {
        int shelves = isShelves == 1 ? 0 : 1;
        return articleMapper.getArticleInfoIsShelves(id, shelves) > 0;
    }

    /**
     * 文章推荐
     *
     * @param id
     * @param isRecommend 0:推荐  1：取消推荐
     * @return
     */
    public boolean getArticleRecommend(String id, Integer isRecommend) {
        int recommend = isRecommend == 1 ? 0 : 1;
        return articleMapper.getArticleInfoRecommend(id, recommend) > 0;
    }

    /**
     * 文章访问
     *
     * @param id
     * @return
     */
    public boolean getArticleVisit(String id) {
        ArticleInfo articleInfo = articleMapper.getArticleInfoById(id);
        if (articleInfo == null) {
            return false;
        }

        int visit_count = articleInfo.getVisit_count();
        articleInfo.setVisit_count(visit_count + 1);
        return articleMapper.updateSelective(articleInfo) > 0;
    }

    /**
     * 文章点赞
     *
     * @param id
     * @return
     */
    public boolean getArticleLike(String id) {
        ArticleInfo articleInfo = articleMapper.getArticleInfoById(id);
        int like_count = articleInfo.getLike_count();
        articleInfo.setLike_count(like_count + 1);
        return articleMapper.updateSelective(articleInfo) > 0;
    }
}
