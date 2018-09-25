package com.oldboy.mr.day02.数据倾斜_重新定义Key;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//两层调用map reduce
public class WCApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        conf.set("student.name","TianYe");

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
        FileInputFormat.addInputPath(job, new Path("D:/wc/2.txt"));

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("D:/wc/out"));

        //设置三个reduce
        job.setNumReduceTasks(3);
        //执行job
        boolean b = job.waitForCompletion(true);

        if(b){
            Job job2 = Job.getInstance(conf);
            job2.setJobName("word count2");
            job2.setJarByClass(WCApp.class);
            job2.setMapperClass(WCMapper2.class);
            job2.setReducerClass(WCReducer2.class);
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(IntWritable.class);
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job2, new Path("D:/wc/out"));
            FileOutputFormat.setOutputPath(job2, new Path("D:/wc/out2"));
            job2.waitForCompletion(true);
        }
    }
}
