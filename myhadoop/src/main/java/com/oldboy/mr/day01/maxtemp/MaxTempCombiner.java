package com.oldboy.mr.day01.maxtemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxTempCombiner extends Reducer<Text,IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        Integer max = Integer.MIN_VALUE;

        for(IntWritable value : values){
            max = Math.max(max,value.get());
        }

        context.write(key, new IntWritable(max));

    }
}
