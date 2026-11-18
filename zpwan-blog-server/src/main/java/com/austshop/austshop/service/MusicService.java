package com.austshop.austshop.service;


import com.austshop.austshop.entity.MusicInfo;
import com.austshop.austshop.mapper.MusicMapper;
import com.austshop.austshop.service.impl.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("musicService")
public class MusicService implements IMusicService {

    @Autowired
    public MusicMapper musicMapper;

    /**
     * 新增
     *
     * @param title
     * @param author
     * @param src
     * @param pic
     * @return
     */
    public boolean handleMusicCreate(String title, String author, String src, String pic) {
        String id = UUID.randomUUID().toString().toLowerCase();
        return musicMapper.handleMusicCreate(id, src, title, pic, author) > 0;
    }

    /**
     * 编辑
     *
     * @param id
     * @param title
     * @param author
     * @param src
     * @param pic
     * @return
     */
    public boolean handleMusicUpdate(String id, String title, String author, String src, String pic) {
        return musicMapper.handleMusicUpdate(id, src, title, pic, author) > 0;
    }

    /**
     * 查列表
     *
     * @param page
     * @param limit
     * @param title
     * @param author
     * @return
     */
    public List<MusicInfo> getMusicList(Integer page, Integer limit, String title, String author) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        return musicMapper.getMusicList(skip, limit, title, author);
    }


    public int getMusicListCount(String title, String author) {
        return musicMapper.getMusicListCount(title, author);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public MusicInfo getMusicInfoById(String id) {
        return musicMapper.getMusicInfoById(id);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean handleMusicDelete(String id) {
        return musicMapper.handleMusicDelete(id) > 0;
    }
}
