import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestScan {

    @Test
  public void read() throws IOException {
        //配置
        Configuration conf = HBaseConfiguration.create();
        //链接
        Connection conn = ConnectionFactory.createConnection(conf);

        //
        Table tb = conn.getTable(TableName.valueOf("test2:t1"));
        //得到行
        for (int i = 1;i<10;i++){
            if(i!=2) {
                Get get = new Get(Bytes.toBytes("row" + i));
                Result rs = tb.get(get);
                if(rs!=null){
                List<Cell> cells = rs.listCells();
                for (Cell cell : cells) {
                    String row = Bytes.toString(CellUtil.cloneRow(cell));
                    String f = Bytes.toString(CellUtil.cloneFamily(cell));
                    String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String value = Bytes.toString(CellUtil.cloneValue(cell));

                    System.out.println(row + "/" + f + "/" + qualifier + "/" + value);
                }
            }
        }
        }
        conn.close();
    }

}
