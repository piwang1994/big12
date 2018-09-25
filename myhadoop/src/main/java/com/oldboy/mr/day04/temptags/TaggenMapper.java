package com.oldboy.mr.day04.temptags;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * 所有文本中的id_tag 进行单词统计
 */
public class TaggenMapper extends Mapper<LongWritable,Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String[] arr = line.split("\t");

        String id = arr[0];

        List<String> list = Util.parseJson(arr[1]);

        if(list.size() > 0){
            for(String tag : list){
                String newKey = id+"_"+tag;
                context.write(new Text(newKey),new IntWritable(1));
            }
        }
    }
}
