package TableFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class TableInputApp {

    public static void main(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();
        conf.set(TableInputFormat.INPUT_TABLE,"ns1:t1");
        conf.set(TableOutputFormat.OUTPUT_TABLE,"ns1:wc");


        Job job = Job.getInstance(conf);

        job.setJobName("TableFormat");
        job.setJarByClass(TableInputApp.class);

        job.setMapperClass(TableInputMapper.class);
        job.setReducerClass(TableInputReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Put.class);

        job.setInputFormatClass(TableInputFormat.class);
        job.setOutputFormatClass(TableOutputFormat.class);



        job.waitForCompletion(true);

    }


}
