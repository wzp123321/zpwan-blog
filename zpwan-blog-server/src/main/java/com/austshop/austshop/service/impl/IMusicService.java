package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.MusicInfo;

import java.util.List;

public interface IMusicService {

    boolean handleMusicCreate(String title, String author, String src, String pic);

    boolean handleMusicUpdate(String id, String title, String author, String src, String pic);

    List<MusicInfo> getMusicList(Integer page, Integer limit, String title, String author);

    int getMusicListCount(String title, String author);

    MusicInfo getMusicInfoById(String id);

    boolean handleMusicDelete(String id);
}
