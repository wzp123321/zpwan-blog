package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.AdminInfo;
import com.austshop.austshop.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAdminService {
    boolean handleAdminLogin(String username, String password);

    boolean handleAdminAdd(String admin_name, String admin_password, Integer role, String avatar_url);

    boolean handleAdminUpdate(String admin_id, String admin_name, String admin_password, String avatar_url);

    List<AdminInfo> getAdminList(Integer page, Integer limit);

    AdminInfo getAdminInfoById(String admin_id);

    boolean getAdminDelete(String admin_id);

    int getTouristTotal();

    boolean getUserUpsAdd(String article_id, String user_id);

    boolean getUserUpsDelete(String article_id, String user_id);

    boolean getUserUpsInfo(String article_id, String user_id);
}
