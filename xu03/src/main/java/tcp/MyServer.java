package tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 
 */
public class MyServer {
	public static void main(String[] args) throws Exception {
		//服务器套接字
		ServerSocket ss = new ServerSocket(8888);
		//
		while(true){
			System.out.println("开始接受..");
			//客户端套接字,阻塞模式
			Socket sock = ss.accept();
			System.out.printf("%s连进来了!!!\r\n" ,getRemoteAddr(sock));
			new CommThread(sock).start();
		}
	}

	static class CommThread extends Thread{
		private Socket sock ;

		public CommThread(Socket sock){
			this.sock = sock ;
		}

		public void run() {
			InputStream in = null;
			try {
				in = sock.getInputStream();
				String addr = getRemoteAddr(sock) ;
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.printf("%s发来消息 : %s\r\n", addr, line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static String getRemoteAddr(Socket sock){
		//得到远程客户端的地址和端口
		InetSocketAddress addr = (InetSocketAddress) sock.getRemoteSocketAddress();
		String ip = addr.getHostName();
		int port = addr.getPort();
		return ip + ":" + port ;
	}
}
