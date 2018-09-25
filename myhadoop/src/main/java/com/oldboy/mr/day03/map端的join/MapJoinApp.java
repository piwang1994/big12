package com.oldboy.mr.day03.map端的join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MapJoinApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS","file:///");

        conf.set("customer.path","D:/reucer端的join/customers.txt");

        Job job = Job.getInstance(conf);

        FileSystem fs = FileSystem.get(conf);


        //设置job名称
        job.setJobName("Map reucer端的join");

        //job入口函数类
        job.setJarByClass(MapJoinApp.class);

        //设置mapper类
        job.setMapperClass(MapJoinMapper.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        Path pin = new Path("D:/reucer端的join/orders.txt");
        Path pout = new Path("D:/reucer端的join/out");



        //设置输入路径
        FileInputFormat.addInputPath(job, pin);


        //设置输出路径
        FileOutputFormat.setOutputPath(job,pout );

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        job.setNumReduceTasks(1);//可以试试2

        //执行job
        job.waitForCompletion(true);


    }
}
