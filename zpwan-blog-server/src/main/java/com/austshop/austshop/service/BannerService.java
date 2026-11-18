package com.austshop.austshop.service;

import com.austshop.austshop.entity.BannerInfo;
import com.austshop.austshop.mapper.BannerMapper;
import com.austshop.austshop.service.impl.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "bannerService")
public class BannerService implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 新增banner
     *
     * @param title
     * @param url
     * @param imgUrl
     * @return
     */
    public boolean getBannerInsert(String title, String url, String imgUrl) {
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setTitle(title);
        bannerInfo.setUrl(url);
        bannerInfo.setImgUrl(imgUrl);
        bannerInfo.setCreate_time(new Date());
        bannerInfo.setModify_time(new Date());
        return bannerMapper.insertSelective(bannerInfo) > 0;
    }

    /**
     * 更新banner
     *
     * @param id
     * @param title
     * @param url
     * @param imrUrl
     * @param isShelves
     * @return
     */
    public boolean getBannerUpate(Integer id, String title, String url, String imrUrl, Integer isShelves) {
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setId(id);
        bannerInfo.setUrl(url);
        bannerInfo.setTitle(title);
        bannerInfo.setImgUrl(imrUrl);
        bannerInfo.setIsShelves(isShelves);
        bannerInfo.setModify_time(new Date());
        return bannerMapper.updatetSelective(bannerInfo) > 0;
    }

    /**
     * 获取banner列表
     * @param isShelves
     * @return
     */
    public List<BannerInfo> getBannerList(Integer isShelves) {
        List<BannerInfo> bannerInfos= bannerMapper.getBannerList(isShelves);
        return bannerInfos;
    }


    /**
     * 请求详情
     * @param id
     * @return
     */
    public BannerInfo getBannerInfoById(Integer id) {
        return bannerMapper.getBannerInfoById(id);
    }
}
