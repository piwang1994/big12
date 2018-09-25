package com.oldboy.mr.day01.maxtemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTempMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String year = line.substring(15,19);
        int temp = Integer.parseInt(line.substring(87,92));

        if(temp != 9999){
            context.write(new Text(year),new IntWritable(temp));
        }
    }
}
