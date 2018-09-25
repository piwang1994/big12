package weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class Data {

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

    public static void deleteKV(String key, String val) throws IOException {
        String f = null;
        String row = null;
        String qualif = null;


        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("weibo:guanzhu"));

        Scan scan = new Scan();
        RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(val + ",\\d*"));
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result rs : scanner) {
            List<Cell> cells = rs.listCells();
            for (Cell cell : cells) {

                f = Bytes.toString(CellUtil.cloneFamily(cell));
                row = Bytes.toString(CellUtil.cloneRow(cell));
                qualif = Bytes.toString(CellUtil.cloneQualifier(cell));


            }
        }
        if(row!=null) {
            Delete delete = new Delete(Bytes.toBytes(row));
            delete.addColumn(Bytes.toBytes(f),Bytes.toBytes(qualif));

            table.delete(delete);
        }
    }



    public static void main(String[] args) {
        putKV("a", "b");

    }


}
