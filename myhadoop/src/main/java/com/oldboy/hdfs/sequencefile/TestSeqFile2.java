package com.oldboy.hdfs.sequencefile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.io.IOException;

public class TestSeqFile2 {

    /**
     * 测试seqFile排序
     * @throws Exception
     */
    @Test
    public void testSort() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path pin = new Path("D:/seq/random.seq");
        Path pout = new Path("D:/seq/数据有序.seq");

        SequenceFile.Sorter sorter = new SequenceFile.Sorter(fs,IntWritable.class,Text.class,conf);

        sorter.sort(pin,pout);

    }

    /**
     * 测试seqFile的文件合并
     */

    @Test
    public void testMerge() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path pin1 = new Path("D:/seq/block1.seq");
        Path pin2 = new Path("D:/seq/block2.seq");
        Path pout = new Path("D:/seq/merge.seq");

        SequenceFile.Sorter sorter = new SequenceFile.Sorter(fs,IntWritable.class,Text.class,conf);

        Path[] p = {pin1,pin2};

        sorter.merge( p,pout);

    }









}
