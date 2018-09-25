package client;

import common.BaseMessage;
import common.ClientChatMessage;
import common.ClientRefreshFriendsMessage;
import util.IConstants;
import util.MessageFactory;

import java.io.IOException;
import java.net.Socket;

public class QQClientCommThread extends Thread {
    public QQClientChatsUI ui;

    public void run(){
        try {
            Socket socket = new Socket(IConstants.QQ_CLIENT_SERVER_IP, IConstants.QQ_CLIENT_SERVER_PORT);
            for(;;){
                BaseMessage msg = MessageFactory.parseServerMesageFromSocket(socket);
                if(msg!=null){
                    System.out.println("收到服务器消息");
                    switch (msg.getMessageType()){
                        case BaseMessage.SERVER_TO_CLIENT_CHAT:
                        {
                            ClientChatMessage msg0 = (ClientChatMessage) msg;
                            ui.updateHistory(msg0.getSenderAddr(),msg0.getMessage());
                            break;
                        }
                        case BaseMessage.SERVER_TO_CLIENT_CHATS:
                        {
                            break;
                        }
                        case BaseMessage.SERVER_TO_CLIENT_REFRESH_FRIENDS:
                        {
                            ClientRefreshFriendsMessage msg0 = (ClientRefreshFriendsMessage) msg;
                            ui.refreshFriendList(msg0.getFriendsList());
                            break;
                        }
                    }
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
