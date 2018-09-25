package HfileMyexample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;


public class HfileApp1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("fs.defaultFs","file:///");
        Connection conn = ConnectionFactory.createConnection(conf);
        Job job = Job.getInstance(conf);
        job.setJobName("Hfile");


        FileInputFormat.addInputPath(job,new Path("D:/wc/duowan_user.txt"));

        job.setJarByClass(HfileApp1.class);
        job.setMapperClass(HfMapper.class);
        job.setReducerClass(HfReducer.class);

        job.setOutputKeyClass(ImmutableBytesWritable.class);
        job.setOutputValueClass(Cell.class);

        job.setOutputFormatClass(HFileOutputFormat2.class);

        HFileOutputFormat2.configureIncrementalLoad(job,conn.getTable(TableName.valueOf("ns1:t1")),
                conn.getRegionLocator(TableName.valueOf("ns1:t1")));
        HFileOutputFormat2.setOutputPath(job,new Path("D:/xxx"));


        job.waitForCompletion(true);

    }
}
