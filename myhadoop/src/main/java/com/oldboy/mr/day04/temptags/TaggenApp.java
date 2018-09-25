package com.oldboy.mr.day04.temptags;

import com.oldboy.mr.day01.wc.WCApp;
import com.oldboy.mr.day01.wc.WCMapper;
import com.oldboy.mr.day01.wc.WCReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TaggenApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("taggen1");

        //job入口函数类
        job.setJarByClass(TaggenApp.class);

        //设置mapper类
        job.setMapperClass(TaggenMapper.class);

        //设置reducer类
        job.setReducerClass(TaggenReducer.class);

        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        //设置输入路径
        FileInputFormat.addInputPath(job, new Path("F:/temptags.txt"));

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("F:/out"));

        //设置三个reduce
        job.setNumReduceTasks(1);
        //执行job
        Boolean b = job.waitForCompletion(true);

        if(b){
            Job job2 = Job.getInstance(conf);
            job2.setJobName("taggen2");
            job2.setJarByClass(TaggenApp.class);
            job2.setMapperClass(TaggenMapper2.class);
            job2.setReducerClass(TaggenReducer2.class);
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(Text.class);
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job2, new Path("F:/out"));
            FileOutputFormat.setOutputPath(job2, new Path("F:/out2"));
            job2.setNumReduceTasks(1);
            job2.waitForCompletion(true);
        }
    }
}
