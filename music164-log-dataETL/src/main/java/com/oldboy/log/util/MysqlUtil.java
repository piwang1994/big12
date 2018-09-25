package com.oldboy.log.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUtil {
    static Connection conn = connPool();

    public static Connection connPool() {
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

    //得到所有指定表的关键字
    public static List<String> getKeywords(String table) {
        ArrayList<String> list = new ArrayList<String>();

        //获取指定类型的所有字段
        try {

            Statement st = conn.createStatement();

            String sql = "select * from table_shadow where tablename=" + "'" + table + "'";

            ResultSet rs = st.executeQuery(sql);

            //通过rs获取到指定行的所有字段
            ResultSetMetaData rsm = rs.getMetaData();
            int columnCount = rsm.getColumnCount();

            rs.next();

            //从2 开始，是因为字段名称在第二列之后，第一列为日志类型
            for (int i = 2; i <= columnCount; i++) {
                String keyword = rs.getString(i);
                if (keyword != null) {
                    list.add(keyword);
                }
            }
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}
