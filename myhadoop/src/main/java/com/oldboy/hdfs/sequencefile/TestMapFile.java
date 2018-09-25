package com.oldboy.hdfs.sequencefile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.io.IOException;

public class TestMapFile {

    /**
     * 写Mapfile文件
     */
    @Test
    public void testWrite() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        String path = "D:/seq/1.map";

        MapFile.Writer writer = new MapFile.Writer(conf,fs,path,IntWritable.class,Text.class);

        for (int i = 1; i < 1000; i++) {
            IntWritable key = new IntWritable(i);
            Text value = new Text("helloworld" + i);
            writer.append(key, value);
        }

        writer.close();
    }

    /**
     * seqFile转换为Mapfile
     */
    @Test
    public void SeqConvert() throws Exception {
        System.setProperty("HADOOP_USER_NAME","centos");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("file:///D:/seq/2.map");
        long cnt = MapFile.fix( fs, p, IntWritable.class, Text.class, false, conf);
        System.out.println(cnt);
    }
}
