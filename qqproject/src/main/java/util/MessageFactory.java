package util;

import common.*;
import server.QQserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

import static util.DataUtil.bytesToint;

public class MessageFactory {
    public static BaseMessage parseClientMessageFromChannel(SocketChannel sc) throws IOException {
        ByteBuffer buf1 = ByteBuffer.allocate(1);
        int len = sc.read(buf1);
        if (len != 1) {
            return null;
        }
        buf1.flip();
        //得到的消息类型
        int msgType = buf1.get(0);
        switch (msgType) {
            case BaseMessage.CLIENT_TO_SERVER_CHAT: {
                ServerChatMessage msg = new ServerChatMessage();
                //接受者地址长度
                buf1.clear();
                sc.read(buf1);
                buf1.flip();
                int recvAddrLen = buf1.get(0);

                //接受者地址
                ByteBuffer bufn = ByteBuffer.allocate(recvAddrLen);
                sc.read(bufn);
                msg.setMessageBytes(bufn.array());

                //信息长度
                ByteBuffer buf4 = ByteBuffer.allocate(4);
                int msgLen = bytesToint(buf4.array());
                //信息
                bufn = ByteBuffer.allocate(msgLen);
                sc.read(bufn);
                msg.setMessageBytes(bufn.array());
                return msg;
            }
            case BaseMessage.CLIENT_TO_SERVER_CHATS: {
                ServerChatsMessage msg = new ServerChatsMessage();
                ByteBuffer buf4 = ByteBuffer.allocate(4);
                sc.read(buf4);
                int msgLen = bytesToint(buf4.array());
                //n个缓冲区字节
                ByteBuffer bufn = ByteBuffer.allocate(msgLen);
                sc.read(bufn);
                msg.setMessageBytes(buf4.array());
                msg.getSendAddrBytes(QQutil.getRemoteAddrBytes(sc.socket()));
                return msg;

            }
            case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS: {
                ServerRefreshFriendsMessage msg = new ServerRefreshFriendsMessage();
                msg.getFriendsBytes(QQserver.getInstance().getFriendsBytes());
                return msg;

            }
        }
        return null;
    }


    public static BaseMessage parseServerMesageFromSocket(Socket s) throws IOException, ClassNotFoundException {
        InputStream is = s.getInputStream();
        byte[] bytes1 = new byte[1];
        is.read(bytes1);
        int msgType = bytes1[0];

        switch (msgType){
            case BaseMessage.SERVER_TO_CLIENT_CHAT:
            {
                ClientChatMessage msg0 = new ClientChatMessage();
                is.read(bytes1);
                int addrLen = bytes1[0];
                byte[] bytesn = new byte[addrLen];
                is.read(bytesn);
                String sendAddr = new String(bytes1);
                byte[] bytes4 = new byte[4];
                int msgLen=DataUtil.bytesToint(bytes4);
                byte[] bytes = new byte[msgLen];
                String msg = new String(bytes);
                msg0.setMessage(msg);
                msg0.setSenderAddr(sendAddr);
                return msg0;
            }
            case BaseMessage.CLIENT_TO_SERVER_CHATS:
            {
                ClientChatsMessage msg0 = new ClientChatsMessage();
                is.read(bytes1);
                int sendAddrLen = bytes1[0];
                byte[] bytesn = new byte[sendAddrLen];
                is.read(bytesn);

                byte[] bytes4 = new byte[4];
                is.read(bytes4);
                int msgLen = DataUtil.bytesToint(bytes4);
                byte[] bytes = new byte[msgLen];
                is.read(bytes);
                msg0.setMessage(new String(bytes));
                msg0.setSenderAddr(new String(bytesn));
                return msg0;
            }
            case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS:
                ClientRefreshFriendsMessage msg0 = new ClientRefreshFriendsMessage();
                byte[] bytes4 = new byte[4];
                is.read(bytes4);
                int listLen = DataUtil.bytesToint(bytes4);
                byte[] bytes = new byte[listLen];
                is.read(bytes);
                List<String> friends = (List<String>) DataUtil.deserialData(bytes);
                msg0.setFriendsList(friends);
                return msg0;

        }
        return null;

    }
}
