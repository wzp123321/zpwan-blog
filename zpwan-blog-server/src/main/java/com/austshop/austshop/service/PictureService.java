package com.austshop.austshop.service;

import com.austshop.austshop.entity.PictureInfo;
import com.austshop.austshop.mapper.PictureMapper;
import com.austshop.austshop.service.impl.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("pictureService")
public class PictureService implements IPictureService {

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 新增照片
     *
     * @param urls
     * @return
     */
    public boolean handlePictureInsert(String urls) {
        String[] pic_urls = urls.split(",");
        boolean flag = true;
        for (int i = 0; i < pic_urls.length; i++) {
            String picture_id = UUID.randomUUID().toString().toLowerCase();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());                    //放入Date类型数据

            int year = calendar.get(Calendar.YEAR);                    //获取年份
            int month = calendar.get(Calendar.MONTH) + 1;               //获取月份
            int day = calendar.get(Calendar.DATE);                      // 获取日期
            String init_date = year + "-" + month + "-" + day;

            flag = pictureMapper.handlePictureInsert(picture_id, pic_urls[i], new Date().getTime(), init_date) > 0;
        }
        return flag;
    }

    /**
     * 请求列表
     *
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @return
     */
    public List<PictureInfo> queryPictureList(Integer page, Integer limit, Long startDate, Long endDate) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        return pictureMapper.handlePictureList(skip, limit, startDate, endDate);
    }

    /**
     * 获取总数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public int queryPictureListTotal(Long startDate, Long endDate) {
        return pictureMapper.handlePictureListTotal(startDate, endDate);
    }

    /**
     * 删除
     *
     * @param picture_id
     * @return
     */
    public boolean handlePictureDelete(String picture_id) {
        return pictureMapper.handlePictureDelete(picture_id) > 0;
    }

    /**
     * 判断下一页是否还有当天数据
     *
     * @return
     */
    public boolean checkPictureListHasNext(Integer page, Integer limit, Long startDate, Long endDate) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        List<PictureInfo> pictureInfoList = pictureMapper.handlePictureList(skip, limit, startDate, endDate);
        return pictureInfoList.size() > 0;
    }
}
