package calllog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Test;

public class ModifyCopro {

    /***
     * 通过代码删除协处理器
     * @throws Exception
     */
    @Test
    public void testDel() throws Exception{
        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接的类
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取hbase管理员
        Admin admin = conn.getAdmin();

        TableName table = TableName.valueOf("calllog");

        admin.disableTable(table);

        HTableDescriptor htd = new HTableDescriptor(table);

        htd.addFamily(new HColumnDescriptor("normal"));
        htd.addFamily(new HColumnDescriptor("profession"));

        admin.modifyTable(table,htd);

        admin.enableTable(table);

        admin.close();
        conn.close();

    }

    /***
     * 通过代码添加协处理器
     * @throws Exception
     */
    @Test
    public void testAdd2() throws Exception{
        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取hbase管理员
        Admin admin = conn.getAdmin();

        TableName table = TableName.valueOf("calllog");

        admin.disableTable(table);

        HTableDescriptor htd = new HTableDescriptor(table);

        htd.addCoprocessor("calllog.CalllogCopro",new Path("/calllog.jar"),0,null);

        htd.addFamily(new HColumnDescriptor("normal"));
        htd.addFamily(new HColumnDescriptor("profession"));

        admin.modifyTable(table,htd);

        admin.enableTable(table);

        admin.close();
        conn.close();

    }




}
