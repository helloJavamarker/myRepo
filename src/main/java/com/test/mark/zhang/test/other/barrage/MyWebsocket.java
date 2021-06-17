package com.test.mark.zhang.test.other.barrage;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Classname MyWebsocket
 * @Description 使用websocket实现弹幕功能
 * 1-前端实现将把信息发送到后台和接收后台发送过来的信息并且将其发射弹幕到浏览器上
 * 2-通过websocket与每个服务端连接，当接收到来自任何一个前端发来的信息，就将该信息群发至所有的客户端(也就是浏览器)
 * @Date 2021/6/7 9:00 上午
 * @Created by mark
 */

//每个客户端连接成功的时候在后台都会创建一个相应的MyWebsocket类  ,这里不用引入新包,Tomcat就有
@ServerEndpoint("/websocket")
public class MyWebsocket {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebsocket> websocketPools = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        websocketPools.add(this);
    }

    @OnClose
    public void onClose() {
        websocketPools.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        for (MyWebsocket item : websocketPools) {
            try {
                item.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void send(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
        //this.session.getBasicRemote().sendText(message);

    }


    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
