package com.oldboy.mr.day03.二次排序;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SortApp {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("secondary 数据有序");

        //job入口函数类
        job.setJarByClass(SortApp.class);

        //设置mapper类
        job.setMapperClass(SortMapper.class);

        //设置reducer类
        job.setReducerClass(SortReducer.class);

//        job.setCombinerClass(SortReducer.class);

        job.setGroupingComparatorClass(MyGroupComparator.class);

        job.setPartitionerClass(MyHashPartition.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(CompKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path pin = new Path("D:/二次排序/kv.txt");
        Path pout = new Path("D:/二次排序/out");



        //设置输入路径
        FileInputFormat.addInputPath(job, pin);


        //设置输出路径
        FileOutputFormat.setOutputPath(job,pout );

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        job.setNumReduceTasks(3);

        //执行job
        job.waitForCompletion(true);

    }



}
