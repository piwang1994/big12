package weibo;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

public class Weibo extends BaseRegionObserver {

    private static final String GUANZHU_TABLE_NAME = "weibo:guanzhu";
    private static final String FENSI_TABLE_NAME = "weibo:fensi";

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {

        //获取环境对象
        RegionCoprocessorEnvironment env = e.getEnvironment();
        //如果表名是关注表
        if(env.getRegionInfo().getTable().getNameAsString().equals(GUANZHU_TABLE_NAME)){

            //得到put的Cell
            //由于put时只有一个数据，即一个cell，所以获取map中第一个cell即可
           // List<Cell> cells = put.getFamilyCellMap().firstEntry().getValue();


            NavigableMap<byte[], List<Cell>> familyCellMap = put.getFamilyCellMap();
            Map.Entry<byte[], List<Cell>> listEntry = familyCellMap.firstEntry();
            List<Cell> cells = listEntry.getValue();
            Cell cell = cells.get(0);

            //从cell中获取rowKey，即关注者
            String a = Bytes.toString(CellUtil.cloneRow(cell));
            String[] arr = a.split(",");

            //从cell中获取value，即被关注者
            String b = Bytes.toString(CellUtil.cloneValue(cell));

            String newa = arr[0];
            String newb = b+","+arr[1];


            //通过当前环境构造新的table对象
            Table table = (Table)env.getTable(TableName.valueOf(FENSI_TABLE_NAME));

            //构造新的put对象，将值对调，写入到粉丝表中
            Put newPut = new Put(Bytes.toBytes(newb));
            newPut.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes(newa));

            table.put(newPut);

            table.close();
        }
    }

    @Override
    public void postDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        RegionCoprocessorEnvironment env = e.getEnvironment();
        if(env.getRegionInfo().getTable().getNameAsString().equals(GUANZHU_TABLE_NAME)){
            NavigableMap<byte[], List<Cell>> familyCellMap = delete.getFamilyCellMap();
            Map.Entry<byte[], List<Cell>> entries = familyCellMap.firstEntry();
            List<Cell> cells = entries.getValue();








        }
    }
}
