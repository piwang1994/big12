package com.oldboy.mr.day04.seq;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenSeq {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("D:/Temp/Temp.seq");

        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, NullWritable.class, Text.class, SequenceFile.CompressionType.BLOCK);

        BufferedReader br = new BufferedReader(new FileReader("D:/Temp/temp"));

        String line = null;

        while((line = br.readLine()) != null){
            NullWritable key = NullWritable.get();
            Text value = new Text(line);
            writer.append(key,value);
        }

        writer.close();


    }
}
