import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MytestFilter {
    @Test
    public void scanData() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("test2:t1"));
        Scan scan = new Scan();
//////        ValueFilter filter1 = new ValueFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes("030")));
//////        RowFilter filter = new RowFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new RegexStringComparator(".*9.*"));
////
////        SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes("f1"),Bytes.toBytes("age"),
////                CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes("030")));
////        FamilyFilter filter2 = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("age")));
////        FilterList filterList1 = new FilterList(FilterList.Operator.MUST_PASS_ONE,filter1, filter2);
////
//////        new SingleColumnValueFilter(Bytes.toBytes("f2"))
////
//////        Scan sc = scan.setFilter(valueFilter);
//////        ResultScanner scanner = table.getScanner(sc);
//        for (Result rs : scanner) {
//            List<Cell> cells = rs.listCells();
//            for (Cell cell : cells) {
//                String row = Bytes.toString(CellUtil.cloneRow(cell));
//                String f = Bytes.toString(CellUtil.cloneFamily(cell));
//                String quali = Bytes.toString(CellUtil.cloneQualifier(cell));
//                String value = Bytes.toString(CellUtil.cloneValue(cell));
//
//                System.out.println(row+"/"+f+"/"+quali+"/"+value);
//            }
//
//        }


    }
}
