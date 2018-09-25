package com.oldboy.mr.day04.temptags;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeSet;


/**
 * 将“123456  技师优秀_10” 和 “123456  味道好_5” 变为
 * <p>
 * 123456   技师优秀_10,味道好_5
 */
public class TaggenReducer2 extends Reducer<Text, Text, Text, Text> {

    /**
     * 通过迭代所有的key进行聚合
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //初始化treeSet进行排序
        TreeSet<CompKey> ts = new TreeSet<CompKey>();

        //拼串过程，初始化sb
        StringBuffer sb = new StringBuffer();

        //迭代所有value并将其k-v组装成compKey发送到TreeSet
        for(Text value : values){
            String[] arr = value.toString().split("_");
            ts.add(new CompKey(arr[0],Integer.parseInt(arr[1])));
        }

        //迭代TreeSet并进行拼串
        for (CompKey compKey : ts) {
            sb.append(compKey.toString() + ",");
        }

        String newVal = sb.toString();

        String s = newVal.substring(0, newVal.length() - 1);

        context.write(key,new Text(s));

        //清空string buffer
        sb.setLength(0);

        ts.clear();

    }
}