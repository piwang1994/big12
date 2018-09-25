package util;

import java.net.InetSocketAddress;
import java.net.Socket;

public class QQutil {

    /*
        得到socket的远程主机
     */
    public static String getRemoteHost(Socket s){
        return ((InetSocketAddress)s.getRemoteSocketAddress()).getAddress().getHostAddress();
    }


    /*
    得到socket的远程端口
     */
    public static int getRemotePort(Socket s){
        return  ((InetSocketAddress)s.getRemoteSocketAddress()).getPort();
    }
    /*
    得到socket远程地址
     */
    public static String getRemoteAddr(Socket s){
        return getRemoteHost(s)+":"+getRemotePort(s);
    }

    /*
    得到socket的远程地址的数组
     */
    public static byte[] getRemoteAddrBytes(Socket s){
        return getRemoteAddr(s).getBytes();
    }
}
