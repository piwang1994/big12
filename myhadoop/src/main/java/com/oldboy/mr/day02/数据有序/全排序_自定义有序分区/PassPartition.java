package com.oldboy.mr.day02.数据有序.全排序_自定义有序分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


//这样有序的定义分区：会发生数据倾斜：

public class PassPartition extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {

        String key = text.toString();
        if (key.compareTo("9") < 0) {
            return 0;
        }
        if (key.compareTo("f") < 0) {
            return 1;
        } else return 2;
    }
}
