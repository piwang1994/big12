package com.oldboy.hdfs.codec;

import com.hadoop.compression.lzo.LzopCodec;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;

public class TestCodec {

    public static void main(String[] args) {
        Class[] clazzes = {
                DeflateCodec.class,
                GzipCodec.class,
                BZip2Codec.class,
                Lz4Codec.class,
                LzopCodec.class,
                SnappyCodec.class
        };

        for(Class clazz : clazzes){
            testCompress(clazz);
            testDecompress(clazz);
        }

    }


    /**
     * 测试压缩
     */
    public static void testCompress(Class clazz) {

        try {

            long start = System.currentTimeMillis();

            Configuration conf = new Configuration();

            //GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream("D:/codec/1.xml.gz"));

            //通过反射获取CompressionCodec对象
            CompressionCodec codec =(CompressionCodec)ReflectionUtils.newInstance(clazz, conf);

            //获得文件扩展名
            String ext = codec.getDefaultExtension();

            //通过codec获取输出流，将文件进行压缩
            CompressionOutputStream cos = codec.createOutputStream(new FileOutputStream("/home/centos/codec/1.xml" + ext));

            //获取输入流
            FileInputStream fis= new FileInputStream("/home/centos/codec/1.xml");

            IOUtils.copyBytes(fis,cos,1024);

            fis.close();
            cos.close();


            System.out.print("压缩编解码器："+ ext + ",压缩时间：" + (System.currentTimeMillis() - start));

            File f = new File("/home/centos/codec/1.xml" + ext);
            System.out.print(",文件长度：" + f.length());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试解压缩
     * @throws Exception
     */
    public static void testDecompress(Class clazz) {

        try {
            long start = System.currentTimeMillis();

            Configuration conf = new Configuration();

            //通过反射获取CompressionCodec对象
            CompressionCodec codec =(CompressionCodec)ReflectionUtils.newInstance(clazz, conf);

            //获得文件扩展名
            String ext = codec.getDefaultExtension();

            //通过codec获取输入流，将文件进行解压缩
            CompressionInputStream cis = codec.createInputStream(new FileInputStream("/home/centos/codec/1.xml"+ext));

            //获取输出流
            FileOutputStream fos = new FileOutputStream("/home/centos/codec/2.xml");

            IOUtils.copyBytes(cis,fos,1024);

            IOUtils.closeStream(fos);

            System.out.println(",解压时间：" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        }




    }



}
