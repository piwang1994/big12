package com.oldboy.log.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MysqlUtil {
    static Connection conn = connPool();

    private static Connection connPool() {
        try {
            ConfUtil confUtil = new ConfUtil();
            String driver = confUtil.driver;
            String url = confUtil.url;
            String username = confUtil.username;
            String password = confUtil.password;

            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            //最大池子
            ds.setMaxPoolSize(10);
            //最小池子
            ds.setMinPoolSize(2);
            //初始化池子连接数
            ds.setInitialPoolSize(3);
            //池子不够用每次从客户端获取的连接数
            ds.setAcquireIncrement(2);

            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //得到music数据总行数
    public static int getLine() {
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from music");

            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    //随机获取一首歌数据
    public static List<String> randomMusic() {

        List<String> list = new ArrayList<String>();
        try {
            Statement st = conn.createStatement();

            int line = getLine();
            Random r = new Random();

            ResultSet rs = st.executeQuery("select * from music where id=" + r.nextInt(line));

            if (rs.next()) {
                list.add(rs.getString(2));
                list.add(rs.getString(3));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


    public static void main(String[] args) {
        System.out.println(randomMusic());
    }
}
