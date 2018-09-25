package HfileMyexample;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class HfReducer extends Reducer<Text,IntWritable,ImmutableBytesWritable,Cell> {
    int sum=0;
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable value : values) {
            sum+=value.get();
        }

        Cell cell = CellUtil.createCell(Bytes.toBytes(key.toString()),
                                        Bytes.toBytes("f1"),
                                        Bytes.toBytes("count"),
                                        System.currentTimeMillis(),
                                        KeyValue.Type.Minimum,
                                        Bytes.toBytes(sum+""),null);

        ImmutableBytesWritable outkey = new ImmutableBytesWritable(Bytes.toBytes(key.toString()));

        context.write(outkey,cell);

    }
}
