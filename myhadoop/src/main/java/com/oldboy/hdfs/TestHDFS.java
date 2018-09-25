package com.oldboy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestHDFS {

    @Test
    public void testRead() throws Exception {

        //初始化配置文件
        Configuration conf = new Configuration();
        //初始化文件系统
        FileSystem fs = FileSystem.get(conf);

        //初始化路径
        Path p = new Path("/1.txt");

        //通过文件系统获取输入流
        //FSDataInputStream是inputStream的装饰流，可以通过普通流方式操纵fis
        FSDataInputStream fis = fs.open(p);

        int len = 0;
        byte[] buf = new byte[1024];

        while ((len = fis.read(buf)) != -1) {
            System.out.print(new String(buf, 0, len));
        }
        fis.close();

    }

    @Test
    public void testRead2() throws Exception {

        //初始化配置文件
        Configuration conf = new Configuration();
        //初始化文件系统
        FileSystem fs = FileSystem.get(conf);

        //初始化路径
        Path p = new Path("/1.sh");

        //通过文件系统获取输入流
        //FSDataInputStream是inputStream的装饰流，可以通过普通流方式操纵fis
        FSDataInputStream fis = fs.open(p);

        FileOutputStream fos = new FileOutputStream("D:/1.txt");

        //通过IOUtils拷贝文件
        IOUtils.copyBytes(fis, fos, 1024);

        fis.close();
        fos.close();
        System.out.println("ok");

    }


    @Test
    public void testWrite() throws IOException {
        System.setProperty("HADOOP_USER_NAME", "centos");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        FSDataOutputStream fos = fs.create(new Path("/2.txt"), (short) 2);

        FileInputStream fis = new FileInputStream("D:/access.log1");

        IOUtils.copyBytes(fis, fos, 1024);

        fos.close();
        fis.close();
    }


    //创建文件夹
    @Test
    public void testMkdir() throws IOException {

        System.setProperty("HADOOP_USER_NAME", "centos");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        boolean b = fs.mkdirs(new Path("/aaa"));

        System.out.println(b);


    }

    //删除文件夹
    @Test
    public void testDelete() throws IOException {

        System.setProperty("HADOOP_USER_NAME", "centos");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        boolean b = fs.delete(new Path("/aaa"),true);

        System.out.println(b);
    }

    //文件末尾追加文件
    @Test
    public void testAppend() throws IOException {

        System.setProperty("HADOOP_USER_NAME", "centos");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        FSDataOutputStream fos = fs.append(new Path("/1.sh"));

        FileInputStream fis = new FileInputStream("D:/access.log");

        IOUtils.copyBytes(fis,fos,1024);

       fis.close();
       fos.close();
    }




    //通过递归列出指定文件夹下的文件或文件夹信息
    public static void testList(String path) {

        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(conf);

            FileStatus[] statuses = fs.listStatus(new Path(path));

            for (FileStatus status : statuses) {
                if (status.isDirectory()) {
                    path = status.getPath().toString();
                    System.out.println(path);
                    testList(path);

                } else {
                    System.out.println(status.getPath().toString());

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testList("/");
    }


}
