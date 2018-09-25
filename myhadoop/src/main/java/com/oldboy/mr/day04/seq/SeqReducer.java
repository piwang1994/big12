package com.oldboy.mr.day04.seq;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SeqReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        Integer max = Integer.MIN_VALUE;

        for (IntWritable value : values) {
            max = Math.max(max, value.get());
        }

        context.write(key, new DoubleWritable(max / 10.0));


    }


}
