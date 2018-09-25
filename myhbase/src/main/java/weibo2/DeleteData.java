package weibo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

public class DeleteData {

     private static void deleteKV(String key, String val) {

        try {
            //初始化hbase 的conf
            Configuration conf = HBaseConfiguration.create();

            //通过连接工厂创建连接
            Connection conn = ConnectionFactory.createConnection(conf);

            HTable table = (HTable) conn.getTable(TableName.valueOf("weibo:guanzhu"));

            Scan scan = new Scan();

            RowFilter filter1 = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(key + ","));
            ValueFilter filter2 = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(val)));

            FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL, filter1, filter2);

            scan.setFilter(fl);

            ResultScanner scanner = table.getScanner(scan);

            for (Result rs : scanner) {
                List<Cell> cells = rs.listCells();
                for (Cell cell : cells) {
                    Delete delete = new Delete(CellUtil.cloneRow(cell));
                    table.delete(delete);
                }
            }
            table.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        deleteKV("a", "c");

    }


}
