package com.oldboy.mr.day05.userdraw;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

public class UserDrawReducer2 extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double p0 = 0;
        double p1 = 0;
        double p2 = 0;
        double p3 = 0;
        double p4 = 0;
        double p5 = 0;
        double p6 = 0;
        for (Text value : values) {
            String[] apparr = value.toString().split("\\|");

            p0 += Double.parseDouble(apparr[0]);
            p1 += Double.parseDouble(apparr[1]);
            p2 += Double.parseDouble(apparr[2]);
            p3 += Double.parseDouble(apparr[3]);
            p4 += Double.parseDouble(apparr[4]);
            p5 += Double.parseDouble(apparr[5]);
            p6 += Double.parseDouble(apparr[6]);
        }

        String newline =p0+"|"+p1+"|"+p2+"|"+p3+"|"+p4+"|"+p5+"|"+p6;

            context.write(key, new Text(newline));



    }
}