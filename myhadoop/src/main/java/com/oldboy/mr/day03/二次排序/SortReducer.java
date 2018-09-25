package com.oldboy.mr.day03.二次排序;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SortReducer extends Reducer<CompKey, NullWritable, Text, IntWritable> {
    @Override
    protected void reduce(CompKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        for(NullWritable  value: values){
            String year = key.getYear();
            int temp = key.getTemp();

            context.write(new Text(year), new IntWritable(temp));

        }

        //把MyGroupComparator去了 填上下面这一句话。。验证分组的必要性
        context.write(new Text("========================================"),new IntWritable(0000));




    }
}
