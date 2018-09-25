import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import sun.misc.FormattedFloatingDecimal;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TestHbase {
    @Test
    public void createNS() throws IOException {
        //初始化Hbase的conf
        Configuration conf = HBaseConfiguration.create();
        //通过连接工厂创建链接
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取hbase的管理员
        Admin admin = conn.getAdmin();

        TableName table = TableName.valueOf("test:t1");
        HTableDescriptor tDes = new HTableDescriptor(table);
        tDes.addFamily(new HColumnDescriptor("f1"));

        admin.createTable(tDes);
        admin.close();
        conn.close();

    }

    @Test
    public void dataPut() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);


        Table table = conn.getTable(TableName.valueOf("test:t1"));

        Put row1 = new Put(Bytes.toBytes("row1"));

        Put put = row1.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("chang"));

        table.put(put);

        table.close();

        conn.close();


    }
    @Test
    public void myputData() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
        //
        TableName tableName = TableName.valueOf("test2:t3");
        //
        HTableDescriptor descr = new HTableDescriptor(tableName);
       //
        descr.addCoprocessor("Myobsever",new Path("/myhbase.jar"),0,null);
        descr.addFamily(new HColumnDescriptor("f1"));
        //
        admin.createTable(descr);
        admin.close();
        //
        HTable table = (HTable)conn.getTable(TableName.valueOf("test2:t3"));
        long l = System.currentTimeMillis();

        for(int i=1;i<100;i++){
            Put put = new Put(Bytes.toBytes("row"+i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom"));
            put.addColumn(Bytes.toBytes(("f1")), Bytes.toBytes("id"), Bytes.toBytes(i+""));
            put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("age"),Bytes.toBytes(i+"yrs"));
            table.put(put);
        }
        table.flushCommits();
        table.close();
        conn.close();

    }

    @Test
    public void putData() throws Exception {

        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接
        Connection conn = ConnectionFactory.createConnection(conf);

        HTable table = (HTable) conn.getTable(TableName.valueOf("test:t2 "));


        //设置自动刷写为false
        table.setAutoFlush(false,false);

//        put列表
        List<Put> list = new LinkedList<Put>();
//        FormattedFloatingDecimal fd = new FormattedFloatingDecimal(4);


        long start = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            //Bytes.toBytes(）可以将任意类型转换成字节数组
            Put put = new Put(Bytes.toBytes("row" + i));
            put.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("name"), Bytes.toBytes("tom" + i));
            put.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("id"), Bytes.toBytes(i + ""));
            put.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("age"), Bytes.toBytes(i  + ""));
            list.add(put);
//            table.put(put);
        }

        table.put(list);

        table.flushCommits();

        table.close();
        conn.close();

        System.out.println(System.currentTimeMillis() - start);
    }

    //数据删除
    @Test
    public void deleteData() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);

        Table t1 = conn.getTable(TableName.valueOf("test:t1"));

         Delete delete = new Delete(Bytes.toBytes("row2"));
         delete.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"));
         delete.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"));
         delete.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"));


        t1.delete(delete);
        t1.close();
        conn.close();
    }




}

