package HfileDIY;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class HFileReducer extends Reducer<Text,IntWritable,ImmutableBytesWritable,Cell> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum=0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        //rowkey
        ImmutableBytesWritable outKey = new ImmutableBytesWritable(Bytes.toBytes(key.toString()));

        //final byte[] row, final byte[] family, final byte[] qualifier,
        // final long timestamp, Type type, final byte[] value, byte[] tags
        Cell cell = CellUtil.createCell(Bytes.toBytes(key.toString()), Bytes.toBytes("f1"), Bytes.toBytes("count"), System.currentTimeMillis(),
                KeyValue.Type.Minimum, Bytes.toBytes(sum + ""), null);

        context.write(outKey,cell);


    }
}
