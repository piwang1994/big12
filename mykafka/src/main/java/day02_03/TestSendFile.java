package day02_03;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TestSendFile {

    @Test
    public void test1() throws Exception{

        FileInputStream fis= new FileInputStream("E:/wc/duowan_user.txt");

        FileOutputStream fos = new FileOutputStream("E:/wc/duowan_user2.txt");

        long start = System.currentTimeMillis();
        int len =0;
        byte[] buf = new byte[1024];

        while((len = fis.read(buf)) != -1){
            fos.write(buf,0,len);
        }
        System.out.println(System.currentTimeMillis() - start);
        //7699
        fis.close();
        fos.close();
    }

    //sendFile
    @Test
    public void test2() throws Exception{

        File f = new File("E:/wc/duowan_user2.txt");

        FileInputStream fis= new FileInputStream(f);

        FileOutputStream fos = new FileOutputStream("E:/wc/duowan_user3.txt");

        FileChannel fc1 = fis.getChannel();
        FileChannel fc2 = fos.getChannel();

        long start = System.currentTimeMillis();
        fc1.transferTo(0,f.length(),fc2);
        System.out.println(System.currentTimeMillis() - start);
        //374
        fc1.close();
        fc2.close();
        fis.close();
        fos.close();
    }
}
