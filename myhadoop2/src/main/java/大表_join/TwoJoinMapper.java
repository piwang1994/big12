package 大表_join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TwoJoinMapper extends Mapper<LongWritable,Text ,Compkey ,Text> {
    private String filename;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit split =(FileSplit) context.getInputSplit();
        filename = split.getPath().getName();



    }
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //这是customer表:1 tom 20
        if(filename.contains("customer")){
            int flag=1;
            String line = value.toString();
            String[] splits = line.split("\t");
            String id = splits[0];
            Compkey compkey = new Compkey(flag, id);
            context.write(compkey,new Text(line));
        }else {//这算是order表 id orderNo cid
            int flag=2;
            String line = value.toString();
            String[] splits = line.split("\t");
            if (splits.length==3){
                String cid = splits[2];

                Compkey compkey = new Compkey(flag, cid);
                context.write(compkey,new Text(line));
            }
        }

    }


}
