package com.oldboy.hdfs.sequencefile;

import com.hadoop.compression.lzo.LzopCodec;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.Lz4Codec;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class TestSeqFile {

    @Test
    public void testWriteSeq() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("D:/seq/lzo.seq");

        //BZip2Codec codec = ReflectionUtils.newInstance(BZip2Codec.class, conf);

        Lz4Codec codec = ReflectionUtils.newInstance(Lz4Codec.class, conf);


        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, Text.class, SequenceFile.CompressionType.BLOCK, codec );

        for (int i = 1; i < 1000000; i++) {
            IntWritable key = new IntWritable(i);
            Text value = new Text("helloworld" + i);
            writer.append(key, value);

        }

        writer.close();

    }

    @Test
    public void testReadSeq() throws Exception {
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("D:/seq/block.seq");

        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p, conf);

        //初始化两个writable对象
        IntWritable key = new IntWritable();
        Text value = new Text();

        while (reader.next(key, value)) {
            long position = reader.getPosition();
            System.out.println("key:" + key.get() + "," + "val:" + value.toString() + "," + "pos:" + position);
        }

    }

    /**
     * 将指针手动移动
     *
     * @throws Exception
     */
    @Test
    public void testSeek() throws Exception {
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("D:/seq/block.seq");

        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p, conf);

        //初始化两个writable对象
        IntWritable key = new IntWritable();
        Text value = new Text();

        //reader.seek(150);
        reader.sync(0);
        reader.next(key,value);
        long position = reader.getPosition();
        System.out.println("key:" + key.get() + "," + "val:" + value.toString() + "," + "pos:" + position);
    }

    @Test
    public void testWrite2() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("D:/seq/2.seq");

        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, Text.class);

        for (int i = 1; i < 1000; i++) {
            IntWritable key = new IntWritable(i);
            Text value = new Text("helloworld" + i);

            writer.append(key, value);
            //在记录边界添加同步点
            writer.sync();
        }
        writer.close();
    }

    /**
     * 创建无序key-value文件
     */
    @Test
    public void testWriteRandom() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("D:/seq/random.seq");

        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, Text.class, SequenceFile.CompressionType.BLOCK);

        //初始化random
        Random r = new Random();

        for (int i = 1; i < 100000; i++) {
            //在0-99999之中随机选取一个值
            int j = r.nextInt(100000);
            IntWritable key = new IntWritable(j);
            Text value = new Text("helloworld" + j);

            writer.append(key, value);
        }
        writer.close();

    }

}



