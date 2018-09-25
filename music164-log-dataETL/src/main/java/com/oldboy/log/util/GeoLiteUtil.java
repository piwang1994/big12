package com.oldboy.log.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class GeoLiteUtil {

    public static Reader reader;

    static Map<String, String> map = new HashMap<String, String>();

    static {
        try {
            //通过资源文件夹获取指定文件的数据流
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("GeoLite2-City.mmdb");

            reader = new Reader(is);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String processIp(String ip) {
        String value = map.get(ip);
        String country = "unknown";
        String province = "unknown";
        if (value == null) {
            try {
                JsonNode jsonNode = reader.get(InetAddress.getByName(ip));

                country = jsonNode.get("country").get("names").get("zh-CN").asText();
                province = jsonNode.get("subdivisions").get(0).get("names").get("zh-CN").asText();

                map.put(ip, country + "," + province);


            } catch (Exception e) {
                map.put(ip, country + "," + province);
            }
        }
        return map.get(ip);
    }

    public static String getCountry(String ip) {
        return processIp(ip).split(",")[0];
    }

    public static String getProvince(String ip) {
        return processIp(ip).split(",")[1];
    }

    public static void main(String[] args) {
        System.out.println(getCountry("199.59.149.136"));
    }


}
