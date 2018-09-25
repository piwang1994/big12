package com.oldboy.log.etl;

import com.oldboy.log.util.GeoLiteUtil;
import com.oldboy.log.util.JsonUtil;
import com.oldboy.log.util.MysqlUtil;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;


@Description(
        name = "parseUsage",
        value = "this is a parseUsage function"
)
public class ParseUsageUDTF extends GenericUDTF {

    //获取日志表所有关键字
    List<String> baseWords = MysqlUtil.getKeywords("appBaseLog");
    List<String> usageWords = MysqlUtil.getKeywords("appUsageLogs");

    PrimitiveObjectInspector poi;

    //定义了表结构
    //argOIs => 输入字段的字段类型
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {

        //判断函数的参数个数
        if (argOIs.getAllStructFieldRefs().size() != 1) {
            throw new UDFArgumentException("参数个数只能为1");
        }

        //如果输入字段类型非String，则抛异常
        ObjectInspector oi = argOIs.getAllStructFieldRefs().get(0).getFieldObjectInspector();

        //判断函数的参数类型
        if (oi.getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentException("参数非基本类型,需要基本类型string");

        }
        //强转为基本类型对象检查器
        poi = (PrimitiveObjectInspector) oi;
        if (poi.getPrimitiveCategory() != PrimitiveObjectInspector.PrimitiveCategory.STRING) {
            throw new UDFArgumentException("参数非string,需要基本类型string");
        }

        //构造字段名,word
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.addAll(baseWords);
        fieldNames.addAll(usageWords);


        //构造字段类型,string
        List<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        //通过基本数据类型工厂获取java基本类型oi
        for (int i = 0; i < fieldNames.size(); i++) {
            fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        }

        //构造对象检查器
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    //生成数据阶段
    @Override
    public void process(Object[] args) throws HiveException {

        int baseSize = baseWords.size();
        int usageSize = usageWords.size();

        Object[] objs = new Object[baseSize+usageSize];

        //得到一行数据
        String line = (String) poi.getPrimitiveJavaObject(args[0]);

        String[] arr = line.split("#");
        if(arr.length == 5 && arr[4] != null){

            //server_time
            objs[0] = arr[0];
            //remote_ip
            objs[1] = arr[1];
            //country
            objs[2] = GeoLiteUtil.getCountry(arr[1]);
            //povince
            objs[3] = GeoLiteUtil.getProvince(arr[1]);
            //client_time
            objs[4] = arr[2];
            for (int i = 5; i <=10; i++) {
                objs[i] = JsonUtil.parseJson(arr[4],baseWords.get(i));
            }
            List<String> usageLogs = JsonUtil.parseJsonToArray(arr[4], "appUsageLogs");
            for (String usageLog : usageLogs) {
                for(int i = 0; i< usageWords.size(); i++){
                    objs[i+11] = JsonUtil.parseJson(usageLog, usageWords.get(i));
                }
                forward(objs);
            }
        }
    }

    //do nothing
    @Override
    public void close() throws HiveException {

    }
}
