package com.oldboy.mr.day02.数据有序.全排序_抽样方法自动排序分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PassMapper extends Mapper<Text,Text,Text,IntWritable> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

        //因为输入格式是KeyValueTextInputFormat.class，k-v对***********************************************************
        context.write(key,new IntWritable(Integer.parseInt(value.toString())));
        //*************************************************************************************************************


    }
}
