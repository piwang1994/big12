package com.oldboy.mr.day01.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition extends Partitioner<Text,IntWritable> {

    /**
     * 自定义分区将数字放在0号分区，其余放在1号分区
     */

    @Override
    public int getPartition(Text key, IntWritable value, int numPartitions) {

        try {
            Integer.parseInt(key.toString());
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
}
