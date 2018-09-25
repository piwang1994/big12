package com.oldboy.mr.day05.userdraw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UserDrawApp {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("userdraw");

        //job入口函数类
        job.setJarByClass(UserDrawApp.class);

        //设置mapper类
        job.setMapperClass(UserDrawMapper.class);

        //设置reducer类
        job.setReducerClass(UserDrawReducer.class);

        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        Path pin = new Path("D:/userdraw/");
        Path pout = new Path("D:/userdrawout");

        //设置输入路径
        FileInputFormat.addInputPath(job, pin);


        //设置输出路径
        FileOutputFormat.setOutputPath(job, pout);

        if (fs.exists(pout)) {
            fs.delete(pout, true);
        }

        job.setNumReduceTasks(1);

        //执行job
        Boolean b = job.waitForCompletion(true);

        if (b) {
            //通过配置文件初始化job
            Job job2 = Job.getInstance(conf);

            //设置job名称
            job2.setJobName("userdraw");

            //job入口函数类
            job2.setJarByClass(UserDrawApp.class);

            //设置mapper类
            job2.setMapperClass(UserDrawMapper2.class);

            //设置reducer类
            job2.setReducerClass(UserDrawReducer2.class);

            //设置map的输出k-v类型
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(Text.class);

            //设置reduce的输出k-v类型
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(Text.class);

            Path pin2 = new Path("D:/userdrawout/");
            Path pout2 = new Path("D:/userdrawout2/");

            //设置输入路径
            FileInputFormat.addInputPath(job2, pin2);

            //设置输出路径
            FileOutputFormat.setOutputPath(job2, pout2);

            if (fs.exists(pout2)) {
                fs.delete(pout2, true);
            }

            job2.setNumReduceTasks(1);

            //执行job
            job2.waitForCompletion(true);
        }
    }
}