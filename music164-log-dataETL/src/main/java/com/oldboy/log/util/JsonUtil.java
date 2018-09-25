package com.oldboy.log.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    String str1 = "{\\\"appChannel\\\":\\\"umeng\\\",\\\"appErrorLogs\\\":[{\\\"createdAtMs\\\":1535697877746,\\\"errorBrief\\\":\\\"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\\",\\\"errorDetail\\\":\\\"java.lang.NullPointerException at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72) at cn.lift.dfdf.web.AbstractBaseController.validInbound\\\"},{\\\"createdAtMs\\\":1535697877746,\\\"errorBrief\\\":\\\"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\\",\\\"errorDetail\\\":\\\"at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606)\\\"}],\\\"appEventLogs\\\":[{\\\"createdAtMs\\\":1535697877747,\\\"eventId\\\":\\\"null\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"0\\\",\\\"musicID\\\":\\\"卡农\\\",\\\"playTime\\\":\\\"1535697877747\\\"},{\\\"createdAtMs\\\":1535697877747,\\\"duration\\\":\\\"00:30\\\",\\\"eventId\\\":\\\"skip\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"-1\\\",\\\"musicID\\\":\\\"木兰辞绯村\\\",\\\"playTime\\\":\\\"1535697877747\\\"},{\\\"createdAtMs\\\":1535697877747,\\\"eventId\\\":\\\"favourite\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"3\\\",\\\"musicID\\\":\\\"Stricken\\\",\\\"playTime\\\":\\\"1535697877747\\\"},{\\\"createdAtMs\\\":1535697877748,\\\"duration\\\":\\\"00:30\\\",\\\"eventId\\\":\\\"skip\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"-1\\\",\\\"musicID\\\":\\\"棠梨煎雪\\\",\\\"playTime\\\":\\\"1535697877748\\\"}],\\\"appPageLogs\\\":[{\\\"createdAtMs\\\":1535697877748,\\\"logType\\\":\\\"page\\\",\\\"nextPage\\\":\\\"test.html\\\",\\\"pageId\\\":\\\"main.html\\\",\\\"pageViewCntInSession\\\":0,\\\"visitIndex\\\":\\\"1\\\"},{\\\"createdAtMs\\\":1535697877748,\\\"logType\\\":\\\"page\\\",\\\"nextPage\\\":\\\"list.html\\\",\\\"pageId\\\":\\\"test.html\\\",\\\"pageViewCntInSession\\\":0,\\\"visitIndex\\\":\\\"0\\\"}],\\\"appPlatform\\\":\\\"ios\\\",\\\"appStartupLogs\\\":[{\\\"brand\\\":\\\"Apple\\\",\\\"carrier\\\":\\\"中国电信\\\",\\\"createdAtMs\\\":1535697877749,\\\"logType\\\":\\\"startup\\\",\\\"network\\\":\\\"3g\\\",\\\"screenSize\\\":\\\"1136 * 640\\\"}],\\\"appUsageLogs\\\":[{\\\"createdAtMs\\\":1535697877749,\\\"logType\\\":\\\"usage\\\",\\\"singleDownloadTraffic\\\":\\\"35\\\",\\\"singleUploadTraffic\\\":\\\"128\\\",\\\"singleUseDurationSecs\\\":\\\"123\\\"},{\\\"createdAtMs\\\":1535697877749,\\\"logType\\\":\\\"usage\\\",\\\"singleDownloadTraffic\\\":\\\"4\\\",\\\"singleUploadTraffic\\\":\\\"128\\\",\\\"singleUseDurationSecs\\\":\\\"234\\\"},{\\\"createdAtMs\\\":1535697877750,\\\"logType\\\":\\\"usage\\\",\\\"singleDownloadTraffic\\\":\\\"3400\\\",\\\"singleUploadTraffic\\\":\\\"35\\\",\\\"singleUseDurationSecs\\\":\\\"123\\\"}],\\\"appVersion\\\":\\\"1.1.0\\\",\\\"deviceId\\\":\\\"Device000026\\\",\\\"deviceStyle\\\":\\\"oppo 1\\\",\\\"osType\\\":\\\"8.3\\\"}";


    public static String parseJson(String str, String key) {
        try {
            String newStr = str.replaceAll("\\\\", "");
            JSONObject jo = JSON.parseObject(newStr);
            Object o = jo.get(key);
            return o.toString();
        } catch (Exception e) {
            return null;
        }

    }

    public static List<String> parseJsonToArray(String str, String key) {

        List<String > list = new ArrayList<String>();
        String newStr = str.replaceAll("\\\\", "");
        JSONObject jo = JSON.parseObject(newStr);
        JSONArray jArray = jo.getJSONArray(key);
        for (Object o : jArray) {
            list.add(o.toString());
        }
        return list;

    }


}
