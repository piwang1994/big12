package com.co.util;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class DictUtil {


    public static Map<String, List<String>> map= new HashMap<String, List<String>>();

    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dictionary.dat");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        ArrayList<String> list = null;
       try{
        while ((line = br.readLine()) != null){
            if(line.startsWith("[")){
                String key = line.substring(1, line.length() - 1);

                list = new ArrayList<String>();

                map.put(key,list);
            }else {
                assert list != null;
                list.add(line);
            }
        }
       }catch (Exception e){
            e.getStackTrace();
       }
    }




    public static String getRandomvalue(Map<String,List<String>> map,String key) {

        List<String> values = map.get(key);
        Random r = new Random();
        try {
            int i = r.nextInt(values.size());
            String value = values.get(i);
            return value;
        }catch (Exception e){
            return null;
        }


    }
}








