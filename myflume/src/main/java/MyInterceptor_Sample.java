import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyInterceptor_Sample implements Interceptor {


    private String type;

    /**
     * Only {@link org.apache.flume.interceptor.HostInterceptor.Builder} can build me
     */

    public MyInterceptor_Sample(String type) {
        this.type = type;
    }


    //拦截代码编写
    //给Event添加头部
    public Event intercept(Event event) {
        Map<String,String> header = new HashMap<String, String>();
        header.put("type",type);
        event.setHeaders(header);
        return event;
    }


    /**
     * Delegates to {@link #intercept(Event)} in a loop.
     *
     * @param events
     * @return
     */
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    public void initialize() {

    }

    public void close() {
        // no-op
    }

    /**
     * Builder which builds new instances of the MyInterceptor.
     */
    public static class Builder implements Interceptor.Builder {

        private String type;

        public void configure(Context context) {
            //日志类型没有默认值
            type = context.getString(Constants.LOG_TYPE);

        }

        public Interceptor build() {
            //在builder中构造方法
            return new MyInterceptor_Sample(type);
        }
    }

    //使用内部类，定义关键字
    public static class Constants {
        //日志类型，非空
        public static final String LOG_TYPE = "logType";
    }

}