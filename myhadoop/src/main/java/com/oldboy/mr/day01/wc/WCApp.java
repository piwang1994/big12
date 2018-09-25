package com.oldboy.mr.day01.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("word count");

        //job入口函数类
        job.setJarByClass(WCApp.class);

        //设置mapper类
        job.setMapperClass(WCMapper.class);

        //设置reducer类
        job.setReducerClass(WCReducer.class);

        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //FileInputFormat.setMaxInputSplitSize(job,10);
        //FileInputFormat.setMinInputSplitSize(job,10);

        //设置输入路径
        Path pin=new Path("E:/wc/2.txt");
        FileInputFormat.addInputPath(job, pin);

        //设置输出路径

        Path pout = new Path("E:/wc/out");
        FileOutputFormat.setOutputPath(job,pout);

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }
        //设置三个reduce
        job.setNumReduceTasks(3);
        //执行job
        job.waitForCompletion(true);
    }

}
