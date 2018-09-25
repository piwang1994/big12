package nio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 */
public class MyNioClient2 {
	public static void main(String[] args) throws Exception {
		Socket sock = new Socket("localhost" , 8888);
		OutputStream out = sock.getOutputStream();
		int index = 0 ;
		for(;;){
			out.write(("hello" + index).getBytes());
			index ++ ;
			Thread.sleep(1000);
		}
	}
}
