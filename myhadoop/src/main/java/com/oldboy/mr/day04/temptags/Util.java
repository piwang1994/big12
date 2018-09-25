package com.oldboy.mr.day04.temptags;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> parseJson(String json) {

        List<String> list = new ArrayList<String>();

        //将json串变成jsonObj格式
        JSONObject jo = JSON.parseObject(json);

        //解析extInfoList
        JSONArray jArray = jo.getJSONArray("extInfoList");
        if(jArray != null && jArray.size() != 0){
            for (Object obj : jArray) {
                //obj => {"title":"contentTags","values":["午餐","分量适中"],"desc":"","defineType":0}
                JSONObject jo2 = (JSONObject) obj;
                if(jo2.get("title").toString().equals("contentTags")){
                    JSONArray jArray2 = jo2.getJSONArray("values");
                    int a=0;
                    if(jArray2 != null && jArray2.size() != 0){
                        for (Object obj2 : jArray2) {
                            list.add(obj2.toString());
                        }
                    }
                }
            }
        }
        return list;
    }
}
