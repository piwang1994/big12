package com.oldboy.mr.day05.multiple;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MaxTempApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("maxTemp");

        //job入口函数类
        job.setJarByClass(MaxTempApp.class);

        //设置mapper类
        job.setMapperClass(MaxTempMapper.class);

        //设置reducer类
        job.setReducerClass(MaxTempReducer.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        Path pin = new Path("D:/mutipleTemp/temp");
        Path pout = new Path("D:/mutipleTemp/out");

//***********************************************************************************************************************************
        //定义两种输出格式
        MultipleOutputs.addNamedOutput(job,"text",TextOutputFormat.class,Text.class,IntWritable.class);
        MultipleOutputs.addNamedOutput(job,"seq",SequenceFileOutputFormat.class,Text.class,DoubleWritable.class);
//************************************************************************************************************************************

        //设置输入路径
        FileInputFormat.addInputPath(job, pin);

        FileOutputFormat.setOutputPath(job,pout );

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        job.setNumReduceTasks(1);

        //执行job
        job.waitForCompletion(true);


    }

}
