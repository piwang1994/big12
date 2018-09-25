package coprocessobserver;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MyObserver extends BaseRegionObserver {

    @Override
    public void postOpen(ObserverContext<RegionCoprocessorEnvironment> e) {
        try {
            FileOutputStream fos = new FileOutputStream("/home/centos/coprocessor.log",true);
            HRegionInfo info = e.getEnvironment().getRegionInfo();
            String tableName = info.getTable().getNameAsString();
            String regionName = info.getRegionNameAsString();
            String line = "表名："+tableName + "\t区域名：" + regionName + "\t这是一个open操作\n";
            fos.write(line.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void postClose(ObserverContext<RegionCoprocessorEnvironment> e, boolean abortRequested) {
        super.postClose(e, abortRequested);
        try {
            FileOutputStream fos = new FileOutputStream("/home/centos/coprocessor.log",true);
            HRegionInfo info = e.getEnvironment().getRegionInfo();
            String tableName = info.getTable().getNameAsString();
            String regionName = info.getRegionNameAsString();
            String line = "表名："+tableName + "\t区域名：" + regionName + "\t这是一个close操作\n";
            fos.write(line.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void postFlush(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("/home/centos/coprocessor.log",true);
            HRegionInfo info = e.getEnvironment().getRegionInfo();
            String tableName = info.getTable().getNameAsString();
            String regionName = info.getRegionNameAsString();
            String line = "表名："+tableName + "\t区域名：" + regionName + "\t这是一个flush操作\n";
            fos.write(line.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void postGetOp(ObserverContext<RegionCoprocessorEnvironment> e, Get get, List<Cell> results) throws IOException {
        super.postGetOp(e, get, results);
        try {
            FileOutputStream fos = new FileOutputStream("/home/centos/coprocessor.log",true);
            HRegionInfo info = e.getEnvironment().getRegionInfo();
            String tableName = info.getTable().getNameAsString();
            String regionName = info.getRegionNameAsString();

            String rowID = new String(get.getRow());

            String line = "表名："+tableName + "\t区域名：" + regionName + "\t行id" + rowID + "\t这是一个get操作\n";
            fos.write(line.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);
        try {
            FileOutputStream fos = new FileOutputStream("/home/centos/coprocessor.log",true);
            HRegionInfo info = e.getEnvironment().getRegionInfo();
            String tableName = info.getTable().getNameAsString();
            String regionName = info.getRegionNameAsString();

            String rowID = new String(put.getRow());

            String line = "表名："+tableName + "\t区域名：" + regionName + "\t行id" + rowID + "\t这是一个put操作\n";
            fos.write(line.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void postDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        super.postDelete(e, delete, edit, durability);
        try {
            FileOutputStream fos = new FileOutputStream("/home/centos/coprocessor.log",true);
            HRegionInfo info = e.getEnvironment().getRegionInfo();
            String tableName = info.getTable().getNameAsString();
            String regionName = info.getRegionNameAsString();

            String rowID = new String(delete.getRow());

            String line = "表名："+tableName + "\t区域名：" + regionName + "\t行id" + rowID + "\t这是一个delete操作\n";
            fos.write(line.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
