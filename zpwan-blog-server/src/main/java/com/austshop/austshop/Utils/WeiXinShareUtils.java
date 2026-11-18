package com.austshop.austshop.Utils;


import com.alibaba.fastjson.JSONObject;
import com.austshop.austshop.entity.WinXinEntity;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeiXinShareUtils {

    //    第三方用户唯一凭证
    public static final String AppId = "wx841716d35606aa11";
    //    第三方用户唯一凭证密钥，即appsecret
    public static final String secret = "bacdc117eebd32a2c73e3c22cbc7fe15";

    private static Logger logger = Logger.getLogger(FileUtils.class);

    //      https://blog.csdn.net/change_on/article/details/75264843
    //      https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html
    //      生成之后在这里校验https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign
    public static WinXinEntity getWinXinEntity(String url) {
        logger.debug("分享链接" + url);
        WinXinEntity wx = new WinXinEntity();
        String access_token = getAccessToken();
        wx.setAccess_token(access_token);
        String ticket = getTicket(access_token);
        Map<String, String> ret = Sign.sign(ticket, url);
        wx.setShareUrl(url);
        wx.setAppId(AppId);
        wx.setTicket(ret.get("jsapi_ticket"));
        wx.setSignature(ret.get("signature"));
        wx.setNonceStr(ret.get("nonceStr"));
        wx.setTimestamp(ret.get("timestamp"));
        logger.debug("获取的分享配置" + wx);
        return wx;
    }

    //获取token
    private static String getAccessToken() {
        String access_token = "";
        String grant_type = "client_credential";//获取access_token填写client_credential
        //这个url链接地址和参数皆不能变
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + AppId + "&secret=" + secret;  //访问链接
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            logger.debug("请求的message" + message);
            JSONObject demoJson = JSONObject.parseObject(message);
            access_token = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }

    //获取ticket
    private static String getTicket(String access_token) {
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";//这个url链接和参数不能变
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.parseObject(message);
            ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
