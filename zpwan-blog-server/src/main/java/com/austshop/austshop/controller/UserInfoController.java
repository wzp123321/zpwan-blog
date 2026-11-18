package com.austshop.austshop.controller;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.Utils.WeiXinShareUtils;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.entity.WinXinEntity;
import com.austshop.austshop.exception.TokenCheckException;
import com.austshop.austshop.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Api(description = "管理员模块")
@RestController
@RequestMapping(value = "/blogManage")
public class UserInfoController {

    @Autowired
    private AdminService userService;


    /**
     * 确认登陆
     *
     * @param httpServletRequest
     * @return
     */
    @ApiOperation(value = "确认登陆")
    @GetMapping(value = "/login/confirm")
    public ResultResponse handleLoginConfirm(
            HttpServletRequest httpServletRequest
    ) {
        String token = httpServletRequest.getHeader("token");
        JsonWebTokenUtils.verifyToken(token);
        return new ResultResponse(200, "SUCCESS");
    }

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "管理员登录")
    @PostMapping(value = "/admin/login")
    public ResultResponse handleLoginByGithub(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        boolean flag = userService.handleAdminLogin(username, password);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag ? JsonWebTokenUtils.createToken(username) : "");
    }

    /**
     * 获取token
     *
     * @param user_id
     * @return
     */
    @ApiOperation(value = "获取token")
    @PostMapping(value = "/token/getById")
    public ResultResponse handleTokenById(
            @RequestParam(value = "user_id", required = true) String user_id
    ) {
        String token = JsonWebTokenUtils.createToken(user_id);
        return new ResultResponse(200, "SUCCESS", token);
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
    @ApiOperation(value = "新增管理员")
    @PostMapping(value = "/admin/add")
    public ResultResponse handleTouristAdd(
            @RequestParam(value = "admin_name", required = false) String admin_name,
            @RequestParam(value = "admin_password", required = true) String admin_password,
            @RequestParam(value = "role", required = true) Integer role,
            @RequestParam(value = "avatar_url", required = true) String avatar_url
    ) {
        return new ResultResponse(200, "SUCCESS", userService.handleAdminAdd(admin_name, admin_password, role, avatar_url));
    }

    /**
     * 更新
     *
     * @param admin_id
     * @param admin_name
     * @param admin_password
     * @param avatar_url
     * @return
     */
    @ApiOperation(value = "更新管理员")
    @PostMapping(value = "/admin/update")
    public ResultResponse handleTouristUpdate(
            @RequestParam(value = "admin_id", required = true) String admin_id,
            @RequestParam(value = "admin_name", required = false) String admin_name,
            @RequestParam(value = "admin_password", required = false) String admin_password,
            @RequestParam(value = "avatar_url", required = false) String avatar_url
    ) {
        boolean flag = userService.handleAdminUpdate(admin_id, admin_name, admin_password, avatar_url);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 获取列表
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "获取管理员列表")
    @GetMapping(value = "/admin/list")
    public ResultResponse handleTouristList(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit
    ) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("data", userService.getAdminList(page, limit));
        modelMap.put("total", userService.getTouristTotal());
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 获取详情
     *
     * @param admin_id
     * @return
     */
    @ApiOperation(value = "获取管理员详情")
    @GetMapping(value = "/admin/getById")
    public ResultResponse handleAdminInfoById(
            @RequestParam(value = "admin_id", required = true) String admin_id
    ) {
        return new ResultResponse(200, "SUCCESS", userService.getAdminInfoById(admin_id));
    }

    /**
     * 删除
     *
     * @param admin_id
     * @return
     */
    @ApiOperation(value = "删除管理员")
    @GetMapping(value = "/admin/delete")
    public ResultResponse handleAdminDeleteById(
            @RequestParam(value = "admin_id", required = true) String admin_id
    ) {
        return new ResultResponse(200, "SUCCESS", userService.getAdminDelete(admin_id));
    }

    /**
     * 点赞文章
     *
     * @param article_id
     * @param user_id
     * @return
     */
    @ApiOperation(value = "点赞文章")
    @PostMapping(value = "/web/ups/add")
    public ResultResponse handleUserUpsAdd(
            @RequestParam(value = "article_id", required = true) String article_id,
            @RequestParam(value = "user_id", required = true) String user_id
    ) {
        boolean flag = userService.getUserUpsAdd(article_id, user_id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 取消点赞
     *
     * @param article_id
     * @param user_id
     * @return
     */
    @ApiOperation(value = "取消点赞")
    @GetMapping(value = "/web/ups/delete")
    public ResultResponse handleUserUpsDelete(
            @RequestParam(value = "article_id", required = true) String article_id,
            @RequestParam(value = "user_id", required = true) String user_id
    ) {
        boolean flag = userService.getUserUpsDelete(article_id, user_id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 校验是否点赞
     *
     * @param article_id
     * @param user_id
     * @return
     */
    @ApiOperation(value = "校验是否点赞")
    @GetMapping(value = "/web/ups/check")
    public ResultResponse handleUserUpsCheck(
            @RequestParam(value = "article_id", required = true) String article_id,
            @RequestParam(value = "user_id", required = true) String user_id
    ) {
        boolean flag = userService.getUserUpsInfo(article_id, user_id);
        return new ResultResponse(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 获取微信分享配置
     *
     * @return
     */
    @ApiOperation(value = "获取微信分享配置")
    @PostMapping(value = "/web/wxshare/config")
    public ResultResponse handleWeiXinShare(
            HttpServletRequest request,
            @RequestParam(value = "url", required = true) String url) {
        WinXinEntity wx = null;
        try {
            wx = WeiXinShareUtils.getWinXinEntity(URLDecoder.decode(url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ModelMap modelMap = new ModelMap();
        modelMap.put("data", wx);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }
}
