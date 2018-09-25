package chang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;

public class ParseJson {

//    deviceId logType createdAtMs eventId mark musicID
    //    {\"appChannel\":\"appstore\",\"appErrorLogs\":[{\"createdAtMs\":1530455040000,\"errorBrief\":\"at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)\",\"errorDetail\":\"at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606)\"},{\"createdAtMs\":1530393180000,\"errorBrief\":\"at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)\",\"errorDetail\":\"at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606)\"}],\"appEventLogs\":[{\"createdAtMs\":1530412800000,\"eventId\":\"share\",\"logType\":\"event\",\"mark\":\"4\",\"musicID\":\"傲红尘\"}],\"appPageLogs\":[{\"createdAtMs\":1530449520000,\"logType\":\"page\",\"nextPage\":\"list.html\",\"pageId\":\"list.html\",\"pageViewCntInSession\":0,\"visitIndex\":\"2\"}],\"appPlatform\":\"ios\",\"appStartupLogs\":[{\"brand\":\"联想\",\"carrier\":\"中国联通\",\"country\":\"china\",\"createdAtMs\":1530385560000,\"logType\":\"startup\",\"network\":\"cell\",\"province\":\"hebei\",\"screenSize\":\"960 * 640\"},{\"brand\":\"魅族\",\"carrier\":\"中国铁通\",\"country\":\"america\",\"createdAtMs\":1530412860000,\"logType\":\"startup\",\"network\":\"3g\",\"province\":\"guangxi\",\"screenSize\":\"480 * 320\"}],\"appUsageLogs\":[{\"createdAtMs\":1530379200000,\"logType\":\"usage\",\"singleDownloadTraffic\":\"12800\",\"singleUploadTraffic\":\"128\",\"singleUseDurationSecs\":\"123\"}],\"appVersion\":\"1.0.0\",\"deviceId\":\"Device000099\",\"deviceStyle\":\"oppo 1\",\"osType\":\"1.4.0\"}
public static void main(String[] args) {
   String json="{\"appChannel\":\"appstore\",\"appErrorLogs\":[{\"createdAtMs\":1530455040000,\"errorBrief\":\"at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)\",\"errorDetail\":\"at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606)\"},{\"createdAtMs\":1530393180000,\"errorBrief\":\"at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)\",\"errorDetail\":\"at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606)\"}],\"appEventLogs\":[{\"createdAtMs\":1530412800000,\"eventId\":\"share\",\"logType\":\"event\",\"mark\":\"4\",\"musicID\":\"傲红尘\"}],\"appPageLogs\":[{\"createdAtMs\":1530449520000,\"logType\":\"page\",\"nextPage\":\"list.html\",\"pageId\":\"list.html\",\"pageViewCntInSession\":0,\"visitIndex\":\"2\"}],\"appPlatform\":\"ios\",\"appStartupLogs\":[{\"brand\":\"联想\",\"carrier\":\"中国联通\",\"country\":\"china\",\"createdAtMs\":1530385560000,\"logType\":\"startup\",\"network\":\"cell\",\"province\":\"hebei\",\"screenSize\":\"960 * 640\"},{\"brand\":\"魅族\",\"carrier\":\"中国铁通\",\"country\":\"america\",\"createdAtMs\":1530412860000,\"logType\":\"startup\",\"network\":\"3g\",\"province\":\"guangxi\",\"screenSize\":\"480 * 320\"}],\"appUsageLogs\":[{\"createdAtMs\":1530379200000,\"logType\":\"usage\",\"singleDownloadTraffic\":\"12800\",\"singleUploadTraffic\":\"128\",\"singleUseDurationSecs\":\"123\"}],\"appVersion\":\"1.0.0\",\"deviceId\":\"Device000099\",\"deviceStyle\":\"oppo 1\",\"osType\":\"1.4.0\"}";
    JSONObject jo = JSON.parseObject(json);
    Object deviceId = jo.get("deviceId");
    JSONArray appEventLogs = jo.getJSONArray("appEventLogs");
//    [{\"createdAtMs\":1530412800000,\"eventId\":\"share\",\"logType\":\"event\",\"mark\":\"4\",\"musicID\":\"傲红尘\"}]
    for(Object ael:appEventLogs){
        JSONObject ael1 = (JSONObject) ael;
        if(ael1.toString().equals("logType")){
            String[] strings = new String[4];

        }
    }
}

}
