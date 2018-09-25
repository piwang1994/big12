package calllog;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalllogCopro extends BaseRegionObserver {

    private static final String CALLLOG_TABLE = "calllog";

    /**
     * 插入主叫normal信息同时生成被叫normal信息refID
     *
     * @param e
     * @param put
     * @param edit
     * @param durability
     * @throws IOException
     */
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);

        RegionCoprocessorEnvironment env = e.getEnvironment();

        String rowKey = new String(put.getRow());
        //事先判断插入的是否是主叫
        //防止循环插入数据
        if (rowKey.contains(",1,")) {
            String[] arr = rowKey.split(",");
            String caller = arr[1];
            String callee = arr[4];
            String time = arr[2];

            String calleeKey = GenKeyUtil.genCallee(callee, caller, time);

            Table table = (Table) env.getTable(TableName.valueOf(CALLLOG_TABLE));

            Put put2 = new Put(Bytes.toBytes(calleeKey));

            put2.addColumn(Bytes.toBytes("normal"), Bytes.toBytes("refId"), Bytes.toBytes(rowKey));

            table.put(put2);
            table.close();
        }

    }

    @Override
    public boolean postScannerNext(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s, List<Result> results, int limit, boolean hasMore) throws IOException {
        boolean b =  super.postScannerNext(e, s, results, limit, hasMore);


        RegionCoprocessorEnvironment env = e.getEnvironment();
        Table table = env.getTable(TableName.valueOf(CALLLOG_TABLE));

        List<Result> list = new ArrayList<Result>();
        list.addAll(results);

        results.clear();

        for (Result result : list) {
            String rowkey = new String(result.getRow());
            //如果rowkey是主叫，则不操作
            if(rowkey.contains(",1,")){
                results.add(result);

            }
            //如果是被叫，则直接获取数据
            else {
                Cell cell = result.listCells().get(0);
                byte[] refID = CellUtil.cloneValue(cell);

                //通过get refID获取到result
                Get get = new Get(refID);
                Result rs = table.get(get);
                List<Cell> cells = rs.listCells();
                ArrayList cells2 = new ArrayList();
                for (Cell cell1 : cells) {
                    byte[] family = cell1.getFamily();
                    byte[] qualifier = cell1.getQualifier();
                    long timestamp = cell1.getTimestamp();
                    byte[] value = cell1.getValue();
                    byte typeByte = cell1.getTypeByte();


                    Cell cell2 = CellUtil.createCell(cell.getRow(), family, qualifier, timestamp,typeByte,value);

                    cells2.add(cell2);
                }
                //                results.add(rs);
            }
        }


        return b;
    }
}
