package weibomytest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class PDdata {

    private static String row= "a";
    private static String value="b";
    public static void main(String[] args) {
        Configuration conf = HBaseConfiguration.create();
        try {
            Connection conn = ConnectionFactory.createConnection(conf);
            Table table = conn.getTable(TableName.valueOf("weibo:guanzhu"));

            putData(table,row ,value);
//            delData(table,row,value);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void putData(Table table,String row ,String value){
        long ts = System.currentTimeMillis();
        Put put = new Put(Bytes.toBytes(row + ","+ts));
        put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes(value));
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static void delData(Table table,String row,String value){
        Scan scan = new Scan();
        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(row + ","));
        ValueFilter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(value));
        FilterList filterList = new FilterList(rowFilter, filter);

        scan.setFilter(filterList);
        try {
            ResultScanner scanner = table.getScanner(scan);

            for (Result rs : scanner) {
                List<Cell> cells = rs.listCells();
                Cell cell = cells.get(0);
                Delete delete = new Delete(CellUtil.cloneRow(cell));
                table.delete(delete);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }




    }
}

