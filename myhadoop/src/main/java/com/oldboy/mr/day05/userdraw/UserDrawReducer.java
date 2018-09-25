package com.oldboy.mr.day05.userdraw;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserDrawReducer extends Reducer<Text,IntWritable,Text,Text> {


    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {


        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        String newVal = key.toString()+ "|" + sum;

        context.write(null,new Text(newVal));
    }
}
