package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties prop;
    static {
        try{
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
            prop = new Properties();
            prop.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    得到String
     */
    public static String getStringValue(String name){
        return prop.getProperty(name);
    }


    /*
    得到int值
     */
    public static int getIntValue(String name){
        return Integer.parseInt(prop.getProperty(name));
    }


    /*
    得到boolen
     */
    public static boolean getBooleanValue(String name){
        return prop.getProperty(name).toLowerCase().equals("true");
    }
}
