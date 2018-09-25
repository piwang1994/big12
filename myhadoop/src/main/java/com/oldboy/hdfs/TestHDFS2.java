package com.oldboy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class TestHDFS2 {
    @Test
    public void testWrite() throws IOException {
        System.setProperty("HADOOP_USER_NAME", "centos");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        FSDataOutputStream fos = fs.create(new Path("/2.txt"));

        FileInputStream fis = new FileInputStream("D:/access.log1");

        IOUtils.copyBytes(fis, fos, 1024);

        fos.close();
        fis.close();
    }
}
