package com.oldboy.log.util;

import com.alibaba.fastjson.JSON;
import com.oldboy.log.common.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class GenLogUtil {

//    public static <T> String  genLog(Class<T> clazz) throws Exception {
    public <T> T genLog(Class<T> clazz) throws Exception {
        Map<String, List<String>> map = DictUtil.map;

        List<String> name_time = MysqlUtil.randomMusic();

        T t1 = clazz.newInstance();

        //如果日志是baseLog的子类，只需要添加createAtMs
        if (t1 instanceof AppBaseLog) {
            //获取所有字段baseLog
            Class clazz2 = AppBaseLog.class;

            Field[] fields2 = clazz2.getDeclaredFields();
            for (Field field : fields2) {
                //设置访问权限
                field.setAccessible(true);
                ((AppBaseLog) t1).setCreatedAtMs(System.currentTimeMillis());
            }
        }


        //如果t1是event日志类型，那么直接设置Event的所有字段

        if (t1 instanceof AppEventLog) {

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //设置访问权限
                field.setAccessible(true);
                //对象设置字段值
                String value = DictUtil.randomValue(map, field.getName().toLowerCase());
                field.set(t1, value);

            }
            if (name_time.size() != 0) {
                ((AppEventLog) t1).setMusicID(name_time.get(0));
                ((AppEventLog) t1).setPlayTime(System.currentTimeMillis() + "");
            }

            //如果事件为听过或主动播放，则设定听歌时长
            if (((AppEventLog) t1).getEventId().equals("play") || ((AppEventLog) t1).getEventId().equals("listen")) {
                if (name_time.size() != 0) {
                    String duration = name_time.get(1);
                    ((AppEventLog) t1).setDuration(duration);
                }
            }
            if (((AppEventLog) t1).getEventId().equals("skip")) {
                if (name_time.size() != 0) {
                    String duration = name_time.get(1);
                    ((AppEventLog) t1).setDuration("00:30");
                }
            }
            String mark = parseMark(((AppEventLog) t1).getEventId());
            ((AppEventLog) t1).setMark(mark);

            //如果不是event日志，则正常随机循环
        } else {
            Field[] fields2 = clazz.getDeclaredFields();
            for (Field field : fields2) {
                if (field.getType() == String.class) {
                    //设置访问权限
                    field.setAccessible(true);
                    String value = DictUtil.randomValue(map, field.getName().toLowerCase());
                    field.set(t1, value);
                }
            }
        }

        return t1;
//        String s = JSON.toJSONString(t1);
//        return s;
    }



    public static String parseMark(String event) {
        switch (event) {
            case "share":
                return "4";
            case "favourite":
                return "3";
            case "play":
                return "2";
            case "listen":
                return "1";
            case "skip":
                return "-1";
            case "nofavourite":
                return "-4";
            case "black":
                return "-5";
            default:
                return "0";
        }
    }

    public static void main(String[] args) throws Exception {
        GenLogUtil genLogUtil = new GenLogUtil();
        for (int i = 0; i < 50; i++) {
            AppEventLog s = genLogUtil.genLog(AppEventLog.class);
            AppErrorLog s2 = genLogUtil.genLog(AppErrorLog.class);
            AppStartupLog s3 = genLogUtil.genLog(AppStartupLog.class);
            AppPageLog s4 = genLogUtil.genLog(AppPageLog.class);
            System.out.println(JSON.toJSONString(s));
            System.out.println(JSON.toJSONString(s2));
            System.out.println(JSON.toJSONString(s3));
            System.out.println(JSON.toJSONString(s4));
            System.out.println("===============================================");
        }
    }
}
