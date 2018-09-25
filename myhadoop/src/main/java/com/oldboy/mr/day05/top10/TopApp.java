package com.oldboy.mr.day05.top10;

import com.oldboy.mr.day01.maxtemp.MaxTempApp;
import com.oldboy.mr.day01.maxtemp.MaxTempMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TopApp {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("num.top", args[3]);

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("top10");

        //job入口函数类
        job.setJarByClass(TopApp.class);

        //设置mapper类
        job.setMapperClass(TopMapper.class);

        //设置reducer类
        job.setReducerClass(TopReducer.class);

        //设置combiner类
        job.setCombinerClass(TopReducer.class);

        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path pin = new Path(args[0]);
        Path pout = new Path(args[1]);

        //设置输入路径
        FileInputFormat.addInputPath(job, pin);


        //设置输出路径
        FileOutputFormat.setOutputPath(job,pout );

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        job.setNumReduceTasks(1);

        //执行job
        Boolean b = job.waitForCompletion(true);

        if(b){
            Job job2 = Job.getInstance(conf);
            job2.setJobName("top10_2");
            job2.setJarByClass(TopApp.class);
            job2.setMapperClass(TopMapper2.class);
            job2.setReducerClass(TopReducer2.class);
            //设置map的输出k-v类型
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(IntWritable.class);
            //设置reduce的输出k-v类型
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(IntWritable.class);

            Path pin2 = new Path(args[1]);
            Path pout2 = new Path(args[2]);
            FileInputFormat.addInputPath(job2, pin2);
            FileOutputFormat.setOutputPath(job2,pout2 );
            if (fs.exists(pout2)) {
                fs.delete(pout2,true);
            }
            job2.setNumReduceTasks(1);
            job2.waitForCompletion(true);

        }
    }

}
