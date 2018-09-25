package bulkload;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

public class BulkLoads {

    public static void main(String[] args) throws Exception{

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        //将数据放在hdfs上
        LoadIncrementalHFiles load = new LoadIncrementalHFiles(conf);
        load.doBulkLoad(new Path("/user/hbase/data/ns1/t1/de5ddf7e103ca1e53ad9bf35fa3f043f"),conn.getAdmin(),
                conn.getTable(TableName.valueOf("ns1:t2")),conn.getRegionLocator(TableName.valueOf("ns1:t2")));

        conn.close();

    }
}
