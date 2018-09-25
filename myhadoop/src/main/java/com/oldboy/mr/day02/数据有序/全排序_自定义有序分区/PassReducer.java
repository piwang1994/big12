package com.oldboy.mr.day02.数据有序.全排序_自定义有序分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


//如果 reduce 个数为n,map(split)个数为map
// 总共调用了（map*n+n）自定义的reduce，其中在Mapper的combiner中map*n
public class PassReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

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