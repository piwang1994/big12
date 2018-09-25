package com.oldboy.hdfs.rackaware;

import java.util.ArrayList;
import java.util.List;

public class TestRack implements org.apache.hadoop.net.DNSToSwitchMapping {

    /**
     * @param names 传入一个主机名或ip地址的列表
     * @return 返回网络拓扑路径/rack1/192.168.23.102
     */
    public List<String> resolve(List<String> names) {

        List<String> list = new ArrayList<String>();

        for(String name : names){
            //如果参数是主机名
            if(name.startsWith("s")){
                //获取后缀
                int suffix = Integer.parseInt(name.substring(1));

                //如果后缀是101-103，则在rack1中
                if(suffix < 104){
                    String path = "/rack1/";
                    list.add(path);
                }
                else {
                    String path = "/rack2/";
                    list.add(path);
                }

            }
            //参数是ip地址192.168.23.101
            else{
                //获取后缀
                int suffix = Integer.parseInt(name.split("\\.")[3]);

                //如果后缀是101-103，则在rack1中
                if(suffix < 104){
                    String path = "/rack1/";
                    list.add(path);
                }
                else {
                    String path = "/rack2/";
                    list.add(path);
                }
            }
        }
        return list;

    }

    public void reloadCachedMappings() {

    }

    public void reloadCachedMappings(List<String> names) {

    }
}
