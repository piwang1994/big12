package HfileDIY;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class HFileApp {

    public static void main(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        conf.set("fs.defaultFS","file:///");


        Job job = Job.getInstance(conf);

        job.setJobName("HFile");
        job.setJarByClass(HFileApp.class);

        job.setMapperClass(HFileMapper.class);
        job.setReducerClass(HFileReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(ImmutableBytesWritable.class);
        job.setOutputValueClass(Cell.class);

        FileInputFormat.addInputPath(job,new Path("D:/wc/duowan_user.txt"));
        job.setOutputFormatClass(HFileOutputFormat2.class);
        HFileOutputFormat2.setOutputPath(job,new Path("F:/xxx"));

        HFileOutputFormat2.configureIncrementalLoad(job,conn.getTable(TableName.valueOf("ns1:wc")),
                conn.getRegionLocator(TableName.valueOf("ns1:wc")));

        job.waitForCompletion(true);
    }


}
