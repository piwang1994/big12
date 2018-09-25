package com.oldboy.mr.day04.db简单;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DBReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    /**
     * 通过迭代所有的key进行聚合
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        for(IntWritable value : values){
            sum += value.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
