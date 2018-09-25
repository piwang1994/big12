package com.oldboy.hdfs.sequencefile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.FileReader;

public class Log2Seq {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("D:/seq/access.seq");

        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, NullWritable.class, Text.class, SequenceFile.CompressionType.BLOCK);

        BufferedReader br = new BufferedReader(new FileReader("D:/seq/access.log1"));

        String line = null;
        while( (line = br.readLine()) != null){
            NullWritable key = NullWritable.get();
            Text value = new Text(line);
            writer.append(key,value);
        }

        writer.close();
    }
}
