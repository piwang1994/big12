package com.oldboy.mr.day04.seq;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SeqMapper extends Mapper<NullWritable,Text,Text,IntWritable> {

    @Override
    //取决于输入seq文件的k-v
    protected void map(NullWritable key, Text value, Context context) throws IOException, InterruptedException {


        String line = value.toString();

        String year = line.substring(15,19);
        int temp = Integer.parseInt(line.substring(87,92));

        if(temp != 9999){
            context.write(new Text(year),new IntWritable(temp));
        }

    }
}
