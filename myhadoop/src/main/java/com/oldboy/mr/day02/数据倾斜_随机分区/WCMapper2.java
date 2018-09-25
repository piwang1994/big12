package com.oldboy.mr.day02.数据倾斜_随机分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper2 extends Mapper<LongWritable, Text, Text, IntWritable> {


    /**
     * map函数，被调用过程是通过while循环每行调用一次
     * 重新设计key
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //将value变为string格式
        String line = value.toString();

        //将一行文本进行截串
        String[] arr = line.split("\t");

        context.write(new Text(arr[0]), new IntWritable(Integer.parseInt(arr[1])));


        //System.out.println(value + "\t" );

    }
}
