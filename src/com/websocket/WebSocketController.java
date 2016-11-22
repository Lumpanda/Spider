package com.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Lumpanda on 2016/11/18.
 */

@Component
@ServerEndpoint(value="/websocket")
public class WebSocketController{

    //记录在线人数。
    private static volatile int onlineCount = 0;
    public static int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void setOnlineCount(int onlineCount) {
        WebSocketController.onlineCount = onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketController.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketController.onlineCount--;
    }



    //存放每个客户端对应的WebSocketController对象。
    //若要1对多，可用Map存放
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();

    public static CopyOnWriteArraySet<WebSocketController> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<WebSocketController> webSocketSet) {
        WebSocketController.webSocketSet = webSocketSet;
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /*
    * 连接成功。一个客户端连接时调用的方法
    * @param session 可选参数。
    * */
    @OnOpen
    public void onOpen( Session session ){

        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println( "有新连接加入。当前在线人数为：" + getOnlineCount() );

    }

    /*
    * 连接关闭。一个客户端退出时调用的方法
    * */
    @OnClose
    public void onClose(){

        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println( "当前连接退出。当前在线人数为：" + getOnlineCount() );

    }

    /*
    * 收到消息。收到客户端消息时调用的方法
    * @param message 客户端发送过来的消息
    * @param session 可选参数
    * */
    @OnMessage
    public void onMessage( String message, Session session ){

        System.out.println( "收到客户端发来的消息： " + message );

        /**/
        //群发消息
        for( WebSocketController item : webSocketSet ){

            try{
                item.sendMessage( message );
            }
            catch(Exception e){
                e.printStackTrace();
                continue;
            }

        }


    }

    /*
    * 发生错误。发生错误时调用的方法
    * @param session
    * @param error
    * */
    @OnError
    public void onError( Session session, Throwable error ){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /*
    * 发送消息。
    * @param message
    * @throws IOException
    * */
    public void sendMessage( String message ) throws IOException{

        System.out.println("发送消息："+message);
        this.session.getBasicRemote().sendText( message );


    }


}













