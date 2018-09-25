package com.oldboy.mr.day05.multiple;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

public class MaxTempMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    MultipleOutputs mos ;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        mos = new MultipleOutputs(context);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String year = line.substring(15, 19);
        int temp = Integer.parseInt(line.substring(87, 92));

        if (temp != 9999) {
            context.write(new Text(year), new IntWritable(temp));
        }

        mos.write("text", new Text(year), new IntWritable(temp), "D:/mutipleTemp/text/");

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}
