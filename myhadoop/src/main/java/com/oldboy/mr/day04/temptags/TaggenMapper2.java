package com.oldboy.mr.day04.temptags;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * 将123456_味道好  5 => 123456 味道好_5
 */
public class TaggenMapper2 extends Mapper<LongWritable,Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String[] arr = line.split("\t");

        String id = arr[0].split("_")[0];
        String tag = arr[0].split("_")[1];
        String sum = arr[1];

        context.write(new Text(id),new Text(tag+"_"+sum));
    }
}
