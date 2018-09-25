package com.oldboy.mr.day05.top10;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeSet;

public class TopReducer2 extends Reducer<Text,IntWritable,Text,IntWritable> {

    int top;
    TreeSet<CompKey> ts;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        top = Integer.parseInt(context.getConfiguration().get("num.top"));
        ts = new TreeSet<CompKey>();
    }

    /**
     * 通过迭代所有的key进行聚合
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        for(IntWritable value : values){

            String pass = key.toString();
            int sum = value.get();

            CompKey ck = new CompKey(pass,sum);

            ts.add(ck);

            if(ts.size() > top){
                ts.remove(ts.last());
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        for (CompKey ck : ts) {

            String pass = ck.getPass();
            int sum = ck.getSum();

            context.write(new Text(pass),new IntWritable(sum));
        }


    }
}
