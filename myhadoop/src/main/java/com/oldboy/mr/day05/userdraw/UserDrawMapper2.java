package com.oldboy.mr.day05.userdraw;

import com.oldboy.mr.day05.userdraw.util.ConfUtil;
import com.oldboy.mr.day05.userdraw.util.ReadAppTab;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class UserDrawMapper2 extends Mapper<LongWritable,Text,Text,Text> {
    Map<String, String> map;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        map = ReadAppTab.readFile();
    }

    /**
     * +/rmMLtMV+s+gXTDoOaoxQ==|10005|824
     * +/rmMLtMV+s+gXTDoOaoxQ==|220499|98
     * +/rmMLtMV+s+gXTDoOaoxQ==|70093|75610
     *
     * AppTab
     * 10005|微信|0.001|0.001|0|0.2|0.3|0.2|0.3
     * 220499|搜狐浏览器|0.001|0.001|0.001|0.002|0.002|0.002|0.003
     * 70093|豌豆荚|0.001|0.001|0.001|0.002|0.002|0.002|0.003
     */

    ConfUtil confUtil=new ConfUtil();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(confUtil.separator);
        String user = splits[0];
        String app_id = splits[1];
        int time = Integer.parseInt(splits[2]);
        String appline = map.get(app_id);
       // 10005|微信|0.001|0.001|0|0.2|0.3|0.2|0.3
        String[] apparr = appline.split("\\|");

        double p0 = Double.parseDouble(apparr[2])*time;
        double p1 = Double.parseDouble(apparr[3])*time;


        Double p2 = Double.parseDouble(apparr[4])*time;
        Double p3 = Double.parseDouble(apparr[5])*time;
        Double p4 = Double.parseDouble(apparr[6])*time;
        Double p5 = Double.parseDouble(apparr[7])*time;
        Double p6 = Double.parseDouble(apparr[8])*time;
        String newline =p0+"|"+p1+"|"+p2+"|"+p3+"|"+p4+"|"+p5+"|"+p6;

        context.write(new Text(user),new Text(newline));
    }
}
