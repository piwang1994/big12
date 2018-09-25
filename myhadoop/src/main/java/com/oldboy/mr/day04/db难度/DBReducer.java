package com.oldboy.mr.day04.db难度;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 新建表，将计算结果插入到表中
 */
public class DBReducer extends Reducer<Text,IntWritable,MyWritable2,NullWritable> {

    /**
     * 通过迭代所有的key进行聚合
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        for(IntWritable value : values){
            sum += value.get();
        }

        //*******************************输出MyWritable**********************************
        MyWritable2 mw = new MyWritable2();
        mw.setWord(key.toString());
        mw.setCount(sum);
        //*******************************************************************

        context.write(mw, NullWritable.get());
    }
}
