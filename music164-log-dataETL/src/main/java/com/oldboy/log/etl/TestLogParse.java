package com.oldboy.log.etl;

import com.oldboy.log.util.GeoLiteUtil;
import com.oldboy.log.util.JsonUtil;
import com.oldboy.log.util.MysqlUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestLogParse {

    @Test
    public void test1() {

        String str1 = "1535697875.562#192.168.13.2##200#";
        String str2 = "1535697875.846#192.168.13.2#1535697877750#200#{\\\"appChannel\\\":\\\"umeng\\\",\\\"appErrorLogs\\\":[{\\\"createdAtMs\\\":1535697877746,\\\"errorBrief\\\":\\\"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\\",\\\"errorDetail\\\":\\\"java.lang.NullPointerException at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72) at cn.lift.dfdf.web.AbstractBaseController.validInbound\\\"},{\\\"createdAtMs\\\":1535697877746,\\\"errorBrief\\\":\\\"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\\",\\\"errorDetail\\\":\\\"at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606)\\\"}],\\\"appEventLogs\\\":[{\\\"createdAtMs\\\":1535697877747,\\\"eventId\\\":\\\"null\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"0\\\",\\\"musicID\\\":\\\"卡农\\\",\\\"playTime\\\":\\\"1535697877747\\\"},{\\\"createdAtMs\\\":1535697877747,\\\"duration\\\":\\\"00:30\\\",\\\"eventId\\\":\\\"skip\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"-1\\\",\\\"musicID\\\":\\\"木兰辞绯村\\\",\\\"playTime\\\":\\\"1535697877747\\\"},{\\\"createdAtMs\\\":1535697877747,\\\"eventId\\\":\\\"favourite\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"3\\\",\\\"musicID\\\":\\\"Stricken\\\",\\\"playTime\\\":\\\"1535697877747\\\"},{\\\"createdAtMs\\\":1535697877748,\\\"duration\\\":\\\"00:30\\\",\\\"eventId\\\":\\\"skip\\\",\\\"logType\\\":\\\"event\\\",\\\"mark\\\":\\\"-1\\\",\\\"musicID\\\":\\\"棠梨煎雪\\\",\\\"playTime\\\":\\\"1535697877748\\\"}],\\\"appPageLogs\\\":[{\\\"createdAtMs\\\":1535697877748,\\\"logType\\\":\\\"page\\\",\\\"nextPage\\\":\\\"test.html\\\",\\\"pageId\\\":\\\"main.html\\\",\\\"pageViewCntInSession\\\":0,\\\"visitIndex\\\":\\\"1\\\"},{\\\"createdAtMs\\\":1535697877748,\\\"logType\\\":\\\"page\\\",\\\"nextPage\\\":\\\"list.html\\\",\\\"pageId\\\":\\\"test.html\\\",\\\"pageViewCntInSession\\\":0,\\\"visitIndex\\\":\\\"0\\\"}],\\\"appPlatform\\\":\\\"ios\\\",\\\"appStartupLogs\\\":[{\\\"brand\\\":\\\"Apple\\\",\\\"carrier\\\":\\\"中国电信\\\",\\\"createdAtMs\\\":1535697877749,\\\"logType\\\":\\\"startup\\\",\\\"network\\\":\\\"3g\\\",\\\"screenSize\\\":\\\"1136 * 640\\\"}],\\\"appUsageLogs\\\":[{\\\"createdAtMs\\\":1535697877749,\\\"logType\\\":\\\"usage\\\",\\\"singleDownloadTraffic\\\":\\\"35\\\",\\\"singleUploadTraffic\\\":\\\"128\\\",\\\"singleUseDurationSecs\\\":\\\"123\\\"},{\\\"createdAtMs\\\":1535697877749,\\\"logType\\\":\\\"usage\\\",\\\"singleDownloadTraffic\\\":\\\"4\\\",\\\"singleUploadTraffic\\\":\\\"128\\\",\\\"singleUseDurationSecs\\\":\\\"234\\\"},{\\\"createdAtMs\\\":1535697877750,\\\"logType\\\":\\\"usage\\\",\\\"singleDownloadTraffic\\\":\\\"3400\\\",\\\"singleUploadTraffic\\\":\\\"35\\\",\\\"singleUseDurationSecs\\\":\\\"123\\\"}],\\\"appVersion\\\":\\\"1.1.0\\\",\\\"deviceId\\\":\\\"Device000026\\\",\\\"deviceStyle\\\":\\\"oppo 1\\\",\\\"osType\\\":\\\"8.3\\\"}";

        //获取所有关键字
        //server_time	remote_ip	country	province	client_time	deviceId	appChannel	appVersion	deviceStyle	osType	appPlatform
        List<String> baseWords = MysqlUtil.getKeywords("appBaseLog");
        List<String> eventWords = MysqlUtil.getKeywords("appEventLogs");

        Map<String, String> map = new HashMap<String, String>();

        String[] arr = str2.split("#");
        if(arr.length == 5 && arr[4] != null){
            map.put(baseWords.get(0), arr[0]);
            map.put(baseWords.get(1), arr[1]);
            map.put(baseWords.get(2), GeoLiteUtil.getCountry(arr[1]));
            map.put(baseWords.get(3), GeoLiteUtil.getProvince(arr[1]));
            map.put(baseWords.get(4),arr[2]);
            for (int i = 5; i <=10; i++) {
                map.put(baseWords.get(i),JsonUtil.parseJson(arr[4],baseWords.get(i)));
            }

            List<String> eventLogs = JsonUtil.parseJsonToArray(arr[4], "appEventLogs");
            String json = eventLogs.get(0);
            for(int i = 0; i<eventWords.size(); i++){
                map.put(eventWords.get(i),JsonUtil.parseJson(json,eventWords.get(i)));
            }

            System.out.println("");

        }




    }


}
