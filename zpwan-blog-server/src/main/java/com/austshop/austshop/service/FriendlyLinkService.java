package com.austshop.austshop.service;


import com.austshop.austshop.entity.FriendlyLinkInfo;
import com.austshop.austshop.mapper.FriendlyLinkMapper;
import com.austshop.austshop.service.impl.IFriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "friendlyLinkService")
public class FriendlyLinkService implements IFriendlyLinkService {
    @Autowired
    private FriendlyLinkMapper friendlyLinkMapper;

    /**
     * 新增友情链接
     *
     * @param name
     * @param url
     * @return
     */
    public boolean getFriendlLinkInsert(String name, String url,Integer isShelves) {
        String ico = url+"/favicon.ico";
        return friendlyLinkMapper.insertFriendltLink(name, ico, url,isShelves, new Date(), new Date()) > 0;
    }

    /**
     * 请求列表
     *
     * @param page
     * @param limit
     * @param name
     * @param isShelves
     * @return
     */
    public List<FriendlyLinkInfo> getFriendlyLinkList(Integer page, Integer limit, String name, Integer isShelves) {
        int skip = page > 0 ? (page - 1) * limit : 0;
        return friendlyLinkMapper.getFriendlyLinkList(skip, limit, name, isShelves);
    }

    /**
     * 请求详情
     *
     * @param id
     * @return
     */
    public FriendlyLinkInfo getFriendlyLinkInfo(Integer id) {
        return friendlyLinkMapper.getFriendlyLinkInfoById(id);
    }

    /**
     * 编辑友情链接
     *
     * @param id
     * @param name
     * @param ico
     * @param url
     * @param isShelves
     * @return
     */
    public boolean getFriendlyLinkUpdate(Integer id, String name, String ico, String url, Integer isShelves) {
        FriendlyLinkInfo friendlyLinkInfo = new FriendlyLinkInfo();
        friendlyLinkInfo.setId(id);
        friendlyLinkInfo.setName(name);
        friendlyLinkInfo.setIco(ico);
        friendlyLinkInfo.setUrl(url);
        friendlyLinkInfo.setIsShelves(isShelves);
        friendlyLinkInfo.setModify_time(new Date());
        return friendlyLinkMapper.updateSelective(friendlyLinkInfo) > 0;
    }

    /**
     * 获取总数
     *
     * @param name
     * @param isShelves
     * @return
     */
    public int getFriendlyLinkTotal(String name, Integer isShelves) {
        return friendlyLinkMapper.getFriendlyLinkTotal(name, isShelves);
    }
}
