package com.oldboy.mr.day02.数据有序.全排序_抽样方法自动排序分区;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

public class PassApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        FileSystem fs = FileSystem.get(conf);

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("word count");

        //job入口函数类
        job.setJarByClass(PassApp.class);

        //设置mapper类
        job.setMapperClass(PassMapper.class);

        //设置reducer类
        job.setReducerClass(PassReducer.class);

        //*********************************************************************************************************
        //设置全排序采样类TotalOrderPartitioner
        job.setPartitionerClass(TotalOrderPartitioner.class);
        //***************************************数据格式严格要求****************************************************************8**


//*****************************************数据格式严格要求**************************************************************8**
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//*******************************************************************************************************8**

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //FileInputFormat.setMaxInputSplitSize(job,10);
        //FileInputFormat.setMinInputSplitSize(job,10);



        //设置输入路径
        FileInputFormat.addInputPath(job, new Path("D:/wc/KeyValueTextInputFormat"));

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("D:/wc/autosortout"));

        if(fs.exists(new Path("D:/wc/autosortout"))){
            fs.delete(new Path("D:/wc/autosortout"),true);
        }

        //设置三个reduce
        job.setNumReduceTasks(3);

        //*************************************************************************************************************
        /**
         * 随机采样，比较浪费性能，耗费资源
         * @param freq 每个key被选择的概率 ，大于采样数(2) / 所有key数量(100)
         * @param numSamples 所有切片中需要选择的key数量
         */
        //设置采样器类型
//        InputSampler.RandomSampler<Text,Text> sampler = new InputSampler.RandomSampler<Text,Text>(0.001,8800);

        /**
         * Create a SplitSampler sampling <em>all</em> splits.
         * Takes the first numSamples / numSplits records from each split.
         * @param numSamples Total number of samples to obtain from all selected splits.
         */
//        InputSampler.SplitSampler<Text,Text> sampler = new InputSampler.SplitSampler<Text,Text>(8800);


        /**
         * Create a new SplitSampler.
         * @param numSamples Total number of samples to obtain from all selected
         *                   splits.
         * @param maxSplitsSampled The maximum number of splits to examine.
         */
        InputSampler.IntervalSampler<Text,Text> sampler = new InputSampler.IntervalSampler<Text,Text>(0.001);  //(double) kept / records < freq

        //设置采样数据地址
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),new Path("D:/wc/par"));

        //写入采样数据  生成par 记录分区里key的首字母
        InputSampler.writePartitionFile(job,sampler);
        //************************************************************************************ ****************************




        //执行job
        boolean b = job.waitForCompletion(true);
    }
}





//***********************************************************************************************************
//    /**
//     * 随机采样，比较浪费性能，耗费资源
//     * @param freq 每个key被选择的概率 ，大于采样数(2) / 所有key数量(100)
//     * @param numSamples 所有切片中需要选择的key数量
//     */
//    //设置采样器类型
//    InputSampler.RandomSampler<Text,Text> sampler = new InputSampler.RandomSampler<Text,Text>(0.001,8800);
//
///**
// * Create a SplitSampler sampling <em>all</em> splits.
// * Takes the first numSamples / numSplits records from each split.
// * @param numSamples Total number of samples to obtain from all selected splits.
// */
////InputSampler.SplitSampler<Text,Text> sampler = new InputSampler.SplitSampler<Text,Text>(10,3);
//
//
///**
// * Create a new SplitSampler.
// * @param numSamples Total number of samples to obtain from all selected
// *                   splits.
// * @param maxSplitsSampled The maximum number of splits to examine.
// */
////InputSampler.IntervalSampler<Text,Text> sampler = new InputSampler.IntervalSampler<Text,Text>(0.001);
//
////设置采样数据地址
//        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),new Path("D:/wc/par/"));
//
//                //写入采样数据
//                InputSampler.writePartitionFile(job,sampler);
//****************************************************************************************************************




