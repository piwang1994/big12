package server;

import common.BaseMessage;
import common.ServerRefreshFriendsMessage;
import util.DataUtil;
import util.IConstants;
import util.QQutil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class QQserver {
    private static QQserver instance;

    private QQserver(){}

    public static QQserver getInstance(){
        if(instance!=null){
            return instance;
        }
        synchronized (QQserver.class){
            if(instance==null){
                instance = new QQserver();
            }
        }
        return instance;
    }

    private HashMap<String,SocketChannel> allClients=new HashMap<String,SocketChannel>();

    /*
    启动服务器
     */
    public void appStart(){
        try{
            //启动线程池
            ExecutorService pool = Executors.newFixedThreadPool(IConstants.QQ_SERVER_THREAD_POOL_CORES);
            //创建通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //配置柱塞模式
            ssc.configureBlocking(IConstants.QQ_SERVER_CHANNEl_BLOCKING_MODE);
            //绑定
            InetSocketAddress addr = new InetSocketAddress(IConstants.QQ_SERVER_BIND_HOST, IConstants.QQ_SERVER_BIND_PORT);
            ssc.bind(addr);
            //开启挑选器
            Selector selector = Selector.open();

            ssc.register(selector,SelectionKey.OP_ACCEPT);
            for(;;){
                //开始挑选
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key0 = it.next();
                    try{
                        //key--ssc
                        if(key0.isAcceptable()){
                            SocketChannel sc0 = ssc.accept();
                            System.out.println("有人上线了");
                            //有新连接
                            sc0.configureBlocking(IConstants.QQ_SERVER_CHANNEl_BLOCKING_MODE);
                            sc0.register(selector,SelectionKey.OP_READ);
                            //创建独占锁
                            ReentrantLock lock = new ReentrantLock();
                            key0.attach(lock);

                            //放置信息到allClients集合中
                            String remoteAddr = QQutil.getRemoteAddr(sc0.socket());
                            allClients.put(remoteAddr,sc0);

                            //广播好友列表
                            broadcastMessage(genFriendListMessage());

                            //常规key ---sc
                            if (key0.isReadable()){
                                pool.execute(new ProcessMessageTask(key0));
                            }
                        }
                    }catch (Exception e){
                        //从通道中取消key---从而删除channel
                        key0.cancel();
                        allClients.remove(QQutil.getRemoteAddr(((SocketChannel)key0.channel()).socket()));
                    }finally {
                        //删除key
                        it.remove();
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //得到好友列表
    public List<String> getFriendsList(){
        return new ArrayList<String>(allClients.keySet());
    }
    //得到好友列表
    public byte[] getFriendsBytes() throws IOException {
        return DataUtil.serialData(getFriendsList());
    }

    //向所有client广播消息
    public void broadcastMessage(BaseMessage msg){
        System.out.println("广播消息");

        for (SocketChannel sc : allClients.values()) {
            try {
                sc.write(ByteBuffer.wrap(msg.popPack()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //转发消息（单发消息）
    public void forwardMessage(BaseMessage msg,String recvAddr) throws Exception {
        SocketChannel sc = allClients.get(recvAddr);
        if(sc!=null){
            sc.write(ByteBuffer.wrap(msg.popPack()));
        }
    }

    //生成好友列表消息
    public ServerRefreshFriendsMessage genFriendListMessage() throws IOException {
        ServerRefreshFriendsMessage msg = new ServerRefreshFriendsMessage();
        msg.setFriendsBytes(getFriendsBytes());
        return msg;
    }


}
