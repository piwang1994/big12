package com.oldboy.mr.day02.数据倾斜_重新定义Key;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Random;

public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Random r = new Random();
    int i;
    String name;


    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取reduce个数
        i = context.getNumReduceTasks();
        name = context.getConfiguration().get("student.name");
    }

    /**
     * map函数，被调用过程是通过while循环每行调用一次
     * 重新设计key
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //将value变为string格式
        String line = value.toString();

        //将一行文本进行截串
        String[] arr = line.split(" ");

        for (String word : arr) {

            String newWord = word + "_" + r.nextInt(i);

            context.write(new Text(newWord), new IntWritable(1));

            System.out.println(name);
        }
        //System.out.println(value + "\t" );

    }
}
