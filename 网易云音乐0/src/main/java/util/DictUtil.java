package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//将字典数据以map形式加载到内存
public class DictUtil {

    public static Map<String, List<String>> map = new HashMap<String, List<String>>();

    static {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dictionary.dat");
        //通过转换流，将字典中的数据变为br
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //
        try {
            List<String> list = null;
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("[")) {
                    String key = line.substring(1, line.length() - 1);
                    list = new ArrayList<String>();
                    map.put(key, list);
                } else {
                    list.add(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static String randomValue(Map<String, List<String>> map, String s) {
        try {
            List<String> strings = map.get(s);
            Random r = new Random();

            int i = r.nextInt(strings.size());

            return strings.get(i);
        } catch (Exception e) {

        }
        return null;

    }
}
