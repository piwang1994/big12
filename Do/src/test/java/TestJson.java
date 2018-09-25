import com.alibaba.fastjson.JSON;
import com.co.common.AppEventLog;
import com.co.util.DictUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class TestJson {
    public static void main(String[] args)throws Exception{
        Map<String, List<String>> map = DictUtil.map;

        AppEventLog eventLog = new AppEventLog();
        Class clazz = AppEventLog.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String randomvalue = DictUtil.getRandomvalue(map, field.getName().toLowerCase());

                field.set(eventLog,randomvalue);

        }
        String s = JSON.toJSONString(eventLog);
        System.out.print(s);





    }

}

