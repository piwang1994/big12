package com.oldboy.mr.day05.top10;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeSet;

public class TopMapper2 extends Mapper<LongWritable, Text, Text, IntWritable> {

    int top;
    TreeSet<CompKey> ts;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        top = Integer.parseInt(context.getConfiguration().get("num.top"));
        ts = new TreeSet<CompKey>();

    }

    //在每个map中取得前十
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] arr = line.split("\t");

        String pass = arr[0];
        int sum = Integer.parseInt(arr[1]);

        CompKey ck = new CompKey(pass, sum);

        ts.add(ck);

        if (ts.size() > top) {
            ts.remove(ts.last());
        }

    }

    //当map完成 且 ts满了之后，将所有的ts进行写出
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        for (CompKey ck : ts) {

            String pass = ck.getPass();
            int sum = ck.getSum();

            context.write(new Text(pass),new IntWritable(sum));
        }
    }
}
