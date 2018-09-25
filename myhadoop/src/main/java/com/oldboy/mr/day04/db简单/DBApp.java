package com.oldboy.mr.day04.db简单;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DBApp {

    public static void main(String[] args) throws Exception {


        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("DB_WC");

        //job入口函数类
        job.setJarByClass(DBApp.class);

        //设置mapper类
        job.setMapperClass(DBMapper.class);

        //设置reducer类
        job.setReducerClass(DBReducer.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        Path pout = new Path("D:/wc/out");
//**********************************************************************************************************************
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/big12";
        String user = "root";
        String pass = "root";

        DBConfiguration.configureDB(job.getConfiguration(),driver,url,user,pass);


        job.setInputFormatClass(DBInputFormat.class);

        //设置输入路径
        DBInputFormat.setInput(job,MyWritable.class,
                "select * from text","select count(*) from text");

//**********************************************************************************************************************
        //设置输出路径
        FileOutputFormat.setOutputPath(job,pout);

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        job.setNumReduceTasks(1);

        //执行job
        job.waitForCompletion(true);


        
    }

}
