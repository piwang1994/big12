package server;

import common.BaseMessage;
import common.ServerChatMessage;
import util.MessageFactory;
import util.QQutil;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessMessageTask implements Runnable {
    private SelectionKey key;
    public ProcessMessageTask(SelectionKey key){
        this.key=key;
    }

    @Override
    public void run() {
        QQserver server = QQserver.getInstance();
        SocketChannel sc = (SocketChannel) key.channel();
        ReentrantLock lock = (ReentrantLock) key.attachment();
        boolean b = lock.tryLock();
        if(b){
            try {
                BaseMessage msg = MessageFactory.parseClientMessageFromChannel(sc);
                if(msg!=null){
                    switch (msg.getMessageType()){
                        case BaseMessage.CLIENT_TO_SERVER_CHATS:
                            server.broadcastMessage(msg);
                            break;
                        case BaseMessage.CLIENT_TO_SERVER_CHAT:
                            server.forwardMessage(msg,new String(((ServerChatMessage)msg).getRecvAddrBytes()));
                            break;
                        case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS:
                            server.forwardMessage(msg,QQutil.getRemoteAddr(sc.socket()));
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
}
