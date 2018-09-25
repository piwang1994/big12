package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {
	public static void main(String[] args) throws Exception {

		FileInputStream fin = new FileInputStream("d:\\java\\1.txt");

		//得到文件通道
		FileChannel fcIn = fin.getChannel();

		//输出部分
		FileOutputStream fout = new FileOutputStream("d:\\java\\1111.txt");
		FileChannel fcOut = fout.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(1024) ;
		while(fcIn.read(buf) != -1){
			buf.flip();
			fcOut.write(buf) ;
			buf.clear();
		}
		fcOut.close();
		fcIn.close();
		fout.close();
		fin.close();
	}
}
