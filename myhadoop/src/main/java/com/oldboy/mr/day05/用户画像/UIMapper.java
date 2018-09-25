package com.oldboy.mr.day05.用户画像;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import sun.plugin.dom.core.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UIMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String path = context.getConfiguration().get("apppath");
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line=br.readLine())!=null){
            String[] arr = line.split("|");

        }

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

    }
}
