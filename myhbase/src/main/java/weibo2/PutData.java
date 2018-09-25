package weibo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class PutData {

    public static void putKV(String key, String val) {

        try {
            //初始化hbase 的conf
            Configuration conf = HBaseConfiguration.create();

            //通过连接工厂创建连接
            Connection conn = ConnectionFactory.createConnection(conf);

            HTable table = (HTable) conn.getTable(TableName.valueOf("weibo:guanzhu"));

            String newKey = key + "," + System.currentTimeMillis();

            Put put = new Put(Bytes.toBytes(newKey));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes(val));

            table.put(put);

            table.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        putKV("a", "b");
    }


}
