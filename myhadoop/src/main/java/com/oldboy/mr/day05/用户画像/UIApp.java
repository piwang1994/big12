package com.oldboy.mr.day05.用户画像;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class UIApp {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.default","file:///");

        conf.set("apppath","D:/app");
        FileSystem fs = FileSystem.get(conf);

        Job job = Job.getInstance();
        job.setJobName("用户画像");

        job.setJarByClass(UIApp.class);
        job.setMapperClass(UIMapper.class);

        Path pin = new Path("D:/userimage");
        FileInputFormat.addInputPath(job,pin);



    }
}
