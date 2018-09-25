package com.oldboy.mr.day02.数据有序.全排序_自定义有序分区;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PassApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("word count");

        //job入口函数类
        job.setJarByClass(PassApp.class);

        //设置mapper类
        job.setMapperClass(PassMapper.class);

        //设置reducer类
        job.setReducerClass(PassReducer.class);


        job.setPartitionerClass(PassPartition.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //FileInputFormat.setMaxInputSplitSize(job,10);
        //FileInputFormat.setMinInputSplitSize(job,10);

        //设置输入路径
        FileInputFormat.addInputPath(job, new Path("D:/wc/duowan_user.txt.bz2"));

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("D:/duowanout/"));

        //设置三个reduce
        job.setNumReduceTasks(3);
        //执行job
        boolean b = job.waitForCompletion(true);

    }
}
