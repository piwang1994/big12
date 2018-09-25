package com.oldboy.mr.day02.数据倾斜_随机分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {


    /**
     * map函数，被调用过程是通过while循环每行调用一次
     * 并没有重新设计key
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //将value变为string格式
        String line = value.toString();

        //将一行文本进行截串
        String[] arr = line.split(" ");

        for (String word : arr) {
            context.write(new Text(word), new IntWritable(1));


        }
        //System.out.println(value + "\t" );

    }
}
