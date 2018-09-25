package com.oldboy.mr.day02.数据倾斜_重新定义Key;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WCReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

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
