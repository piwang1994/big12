package com.oldboy.mr.day03.reucer端的join;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ReduceJoinApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("reucer端的join job");

        //job入口函数类
        job.setJarByClass(ReduceJoinApp.class);

        //设置mapper类
        job.setMapperClass(ReduceJoinMapper.class);

        job.setReducerClass(ReduceJoinReducer.class);

        job.setGroupingComparatorClass(GroupComparator.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(CompKey.class);
        job.setMapOutputValueClass(Text.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        Path pin = new Path("D:/join2/");
        Path pout = new Path("D:/join2/out/");


        //设置输入路径
        FileInputFormat.addInputPath(job, pin);


        //设置输出路径
        FileOutputFormat.setOutputPath(job, pout);

        job.setNumReduceTasks(1);

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        //执行job
        job.waitForCompletion(true);

    }
}
