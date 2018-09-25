package com.oldboy.mr.day05.multiple;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

public class MaxTempReducer extends Reducer<Text,IntWritable,Text,DoubleWritable> {

    MultipleOutputs mos ;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        mos = new MultipleOutputs(context);
    }
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        Integer max = Integer.MIN_VALUE;

        for(IntWritable value : values){
            max = Math.max(max,value.get());
        }

        context.write(key, new DoubleWritable(max / 10.0));

        mos.write("seq", key, new DoubleWritable(max / 10.0), "D:/Temp/seq" );

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}
