import com.alibaba.fastjson.JSON;
import com.oldboy.log.common.AppBaseLog;
import com.oldboy.log.common.AppEventLog;
import com.oldboy.log.util.DictUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class TestGenJson {

    @Test
    public void test1() throws Exception {
        Map<String, List<String>> map = DictUtil.map;
//        for (String key : map.keySet()) {
//            List<String> values = map.get(key);
//            System.out.println("key:"+key+"--"+"values:"+values);
//        }

        AppEventLog eventLog = new AppEventLog();

        //如果Event不是baseLog的子类，那么直接设置Event的所有字段
        Class clazz = AppEventLog.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //设置访问权限
            field.setAccessible(true);
            //对象设置字段值
            String value = DictUtil.randomValue(map, field.getName().toLowerCase());
            field.set(eventLog, value);
        }
        String s = JSON.toJSONString(eventLog);
        System.out.println(s);


        //如果是子类，把base的字段也要加上
        if (eventLog instanceof AppBaseLog) {
            //获取所有字段baseLog+EventLog

            Class clazz2 = AppBaseLog.class;

            Field[] fields2 = clazz2.getDeclaredFields();
            for (Field field : fields2) {
                //设置访问权限
                field.setAccessible(true);
                if(!(field.getModifiers()+"").contains("5")){
                    String value = DictUtil.randomValue(map, field.getName().toLowerCase());
                    field.set(eventLog,value);
                }
            }

        }
        s = JSON.toJSONString(eventLog);
        System.out.println(s);
    }
}


