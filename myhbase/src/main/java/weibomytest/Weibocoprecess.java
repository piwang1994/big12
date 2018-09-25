package weibomytest;

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

public class Weibocoprecess extends BaseRegionObserver {
    private String table_name="weibo:fensi";


    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);
        Cell cell = put.getFamilyCellMap().firstEntry().getValue().get(0);

        String[] split = new String(CellUtil.cloneRow(cell)).split(",");

        String new_row=new String(CellUtil.cloneValue(cell)) + ","+split[1];

        Table table = (Table)e.getEnvironment().getTable(TableName.valueOf(this.table_name));

        Put put1 = new Put(Bytes.toBytes(new_row));

        put1.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes(split[0]));

        table.put(put1);

        table.close();


    }

    @Override
    public void postDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        super.postDelete(e, delete, edit, durability);

        Cell cell = delete.getFamilyCellMap().firstEntry().getValue().get(0);

        String[] split = new String(CellUtil.cloneRow(cell)).split(",");

        byte[] family = CellUtil.cloneFamily(cell);
        byte[] qualifier =CellUtil.cloneQualifier(cell);
        byte[] value = CellUtil.cloneValue(cell);

        String new_row=new String(value) + "," + split[1];
        Table table = (Table)e.getEnvironment().getTable(TableName.valueOf(table_name));
        Delete delete1 = new Delete(Bytes.toBytes(new_row));
        delete1.addColumn(family,qualifier);

        table.delete(delete1);
        table.close();

    }
}
