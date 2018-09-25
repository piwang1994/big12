package coprocessobserver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class TestAddCopro {

    public static void main(String[] args) throws Exception{
        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取hbase管理员
        Admin admin = conn.getAdmin();

        TableName table = TableName.valueOf("test:t3");

        HTableDescriptor htd = new HTableDescriptor(table);

        //本地测试1.上传HDFS  2.
        htd.addCoprocessor("coprocessobserver.MyObserver",new Path("/mybase.jar"),0,null);

        htd.addFamily(new HColumnDescriptor("f1"));

//        admin.createTable(htd);
        admin.modifyTable(table,htd);

        admin.close();
        conn.close();

    }



}
