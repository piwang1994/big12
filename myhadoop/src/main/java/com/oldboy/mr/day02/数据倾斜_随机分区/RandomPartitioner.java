package com.oldboy.mr.day02.数据倾斜_随机分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Random;

public class RandomPartitioner extends Partitioner<Text,IntWritable> {

    Random r = new Random();


    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        return r.nextInt(numPartitions);
    }
}
