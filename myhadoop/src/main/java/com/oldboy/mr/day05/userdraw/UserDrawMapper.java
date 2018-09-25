package com.oldboy.mr.day05.userdraw;

import com.oldboy.mr.day05.userdraw.util.ConfUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class UserDrawMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    ConfUtil confUtil = new ConfUtil();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] arr = line.split(confUtil.separator);

        //获取手机号
        String phone = arr[Integer.parseInt(confUtil.phone)];

        //获取appid
        String appid = arr[Integer.parseInt(confUtil.appid)];

        //获取时间戳
        String ts = arr[Integer.parseInt(confUtil.time)];

        //获取使用时长
        String duration = arr[Integer.parseInt(confUtil.duration)];

        if(phone != null && appid != null && !phone.equals("") && !appid.equals("")){

            Text newKey = new Text(phone + "|" +  Integer.parseInt(appid));

            IntWritable newVal = new IntWritable(Integer.parseInt(duration));

            context.write(newKey,newVal);
            //phone|appid : duration
        }
    }
}
