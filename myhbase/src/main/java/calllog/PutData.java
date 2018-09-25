package calllog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PutData {

    public static void main(String[] args) throws Exception{


        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);


        TableName name = TableName.valueOf("calllog");
        Table table = conn.getTable(name);

        //putCaller(table, ts, caller ,callee ,duration , status );
        putCaller(table, "20180806083002", "13843838438" ,"13800000000" ,"60" , "ok" );


        table.close();
    }

    private static void putCaller(Table table, String time, String caller, String callee, String duration, String status) {

        try {
            String callerKey = GenKeyUtil.genCaller(caller, callee, time);

            Put put = new Put(Bytes.toBytes(callerKey));
            put.addColumn(Bytes.toBytes("normal"),Bytes.toBytes("caller"),Bytes.toBytes(caller));
            put.addColumn(Bytes.toBytes("normal"),Bytes.toBytes("callee"),Bytes.toBytes(callee));
            put.addColumn(Bytes.toBytes("normal"),Bytes.toBytes("time"),Bytes.toBytes(parseTime(time)));
            put.addColumn(Bytes.toBytes("normal"),Bytes.toBytes("duration"),Bytes.toBytes(duration));
            put.addColumn(Bytes.toBytes("normal"),Bytes.toBytes("status"),Bytes.toBytes(status));

            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String parseTime(String time){

        try {
            //将字符串解析为时间戳
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            Date date = sdf.parse(time);

            long ts = date.getTime();

            return ts+"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0+"";
    }

    @Test
    public void testParseTime(){
        System.out.println(parseTime("20180816083002"));
    }







}
