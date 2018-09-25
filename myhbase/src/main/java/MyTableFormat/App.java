package MyTableFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        Job job = Job.getInstance(conf);


        job.setInputFormatClass(TableInputFormat.class);
    }
}
