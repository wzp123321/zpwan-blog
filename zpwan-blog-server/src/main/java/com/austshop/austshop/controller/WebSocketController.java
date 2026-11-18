package com.austshop.austshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.austshop.austshop.Utils.FileUtils;
import com.austshop.austshop.entity.MessageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketController {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        logger.info("websocket连接成功" + session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info("有一连接关闭");
        webSocketSet.remove(this);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("来自客户端的消息:" + message);
    }

    /**
     * 群发自定义消息
     */
    public void sendInfo(Integer type, String title, String article_id) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setType(type);
        messageInfo.setTitle(title);
        messageInfo.setArticle_id(article_id);
        String message = JSONObject.toJSONString(messageInfo);
        for (WebSocketController item : webSocketSet) {
            try {
                System.out.println("message" + message);
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
