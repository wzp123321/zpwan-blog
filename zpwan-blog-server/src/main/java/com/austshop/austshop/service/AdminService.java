package com.austshop.austshop.service;

import com.austshop.austshop.Utils.FileUtils;
import com.austshop.austshop.entity.AdminInfo;
import com.austshop.austshop.mapper.AdminMapper;
import com.austshop.austshop.mapper.UserUpsMapper;
import com.austshop.austshop.service.impl.IAdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value = "adminService")
public class AdminService implements IAdminService {

    private static Logger logger = Logger.getLogger(AdminService.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserUpsMapper userUpsMapper;

    @Autowired
    private ArticleService articleService;

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @return
     */
    public boolean handleAdminLogin(String username, String password) {
        boolean flag = false;
        AdminInfo adminInfo = adminMapper.getAdminInfoByAdminName(username);
        if (adminInfo != null && adminInfo.getAdmin_password().equals(password)) {
            flag = true;
        }
        logger.info("管理员" + username + "登录了");
        return flag;
    }

    /**
     * 新增管理员
     *
     * @param admin_name
     * @param admin_password
     * @param role
     * @param avatar_url
     * @return
     */
    public boolean handleAdminAdd(String admin_name, String admin_password, Integer role, String avatar_url) {
        logger.info("新增了管理员" + admin_name);
        return adminMapper.getAdminInsert(UUID.randomUUID().toString().toLowerCase(), admin_name,
                admin_password, role, avatar_url, new Date(), new Date()) > 0;
    }

    /**
     * 编辑
     *
     * @param admin_name
     * @param admin_password
     * @param avatar_url
     * @return
     */
    public boolean handleAdminUpdate(String admin_id, String admin_name, String admin_password, String avatar_url) {
        return adminMapper.getAdminInfoUpdate(admin_id, admin_name, admin_password, avatar_url, new Date()) > 0;
    }

    /**
     * @param admin_id
     * @return
     */
    public AdminInfo getAdminInfoById(String admin_id) {
        return adminMapper.getAdminInfoById(admin_id);
    }

    /**
     * @param page
     * @param limit
     * @return
     */
    public List<AdminInfo> getAdminList(Integer page, Integer limit) {
        int skip = page > 0 ? (page - 1) * limit : 0;
        return adminMapper.getAdminList(skip, limit);
    }

    /**
     * 删除管理员
     *
     * @param admin_id
     * @return
     */
    public boolean getAdminDelete(String admin_id) {
        AdminInfo adminInfo = adminMapper.getAdminInfoById(admin_id);
        boolean flag = true;
        if (adminInfo.getRole() == 2) {
            flag = false;
        }
        logger.info(new Date() + "删除管理员" + admin_id);
        return !flag ? flag : adminMapper.getAdminDelete(admin_id) > 0;
    }

    /**
     * 查询总数
     *
     * @return
     */
    public int getTouristTotal() {
        return adminMapper.getAdminTotal();
    }

    /**
     * 新增用户点赞记录
     *
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean getUserUpsAdd(String article_id, String user_id) {
        articleService.getArticleLike(article_id);
        return userUpsMapper.insertUserUps(article_id, user_id, new Date(), new Date()) > 0;
    }

    /**
     * 删除用户点赞记录
     *
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean getUserUpsDelete(String article_id, String user_id) {
        return userUpsMapper.deleteUserUps(article_id, user_id) > 0;
    }

    /**
     * 查询用户点赞记录
     *
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean getUserUpsInfo(String article_id, String user_id) {

        return userUpsMapper.getUserUpsInfo(article_id, user_id) != null;
    }
}
