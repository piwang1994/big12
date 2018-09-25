package com.oldboy.mr.day05.userdraw.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfUtil {

    static Properties prop ;

    static {

        prop = new Properties();

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("userdraw.properties");

        try {
            prop.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String separator = prop.getProperty("separator");
    public String phone = prop.getProperty("phone");
    public String appid = prop.getProperty("appid");
    public String time = prop.getProperty("time");
    public String duration = prop.getProperty("duration");
    public String appTab = prop.getProperty("appTab");
    public String filesystem = prop.getProperty("filesystem");






}
