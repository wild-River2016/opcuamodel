package com.mj.k4.config.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketService<T> {
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketService> webSockets = new CopyOnWriteArraySet<>();
    private Session session;

    /**
     * 建立连接成功回调的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        addOnlineCount();
//        log.debug("当前连接数：" + onlineCount + ",时间：" + com.mj.common.util.time.Time.getTimestamp());
        try {
            sendMessage("success", (T) "连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        webSockets.remove(this);
        subOnlineCount();
        log.debug("关闭连接,当前连接数：" + onlineCount);
    }

    /**
     * 接收前端的消息后调用
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        log.debug("onMessage");
//        User user = new User(1, "张三");
//        System.out.println("收到消息" + message);
//        for (WebSocketService socket : webSockets) {
//            try {
//                socket.sendMessage("topic", user);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                onError(session, e);
//            }
//        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.debug("发生错误");
        error.printStackTrace();
    }

    //发送消息
    public void sendMessage(String topic, T date) throws IOException {
        BaseMessage baseMessage = new BaseMessage(topic, date);
        ObjectMapper mapper = new ObjectMapper();
        String msg = mapper.writeValueAsString(baseMessage);
//        log.debug("msg+" + session.getId());
        this.session.getBasicRemote().sendText(msg);
    }

    //发送消息
    public void sendInfo(String topic, T date) throws IOException {
        for (WebSocketService item : webSockets) {
            try {
                item.sendMessage(topic, date);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }
}
