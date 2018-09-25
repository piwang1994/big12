package mypool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnPool {
    private static ConnPool instance=null;
    private static List<Connection> pool=new ArrayList<Connection>();  ;
    private static AtomicInteger busies=new AtomicInteger(0);
    private int MIN=3;
    private int MAX=10;


    public static ConnPool getInstance(){
        if (instance!=null){
            return instance;
        }

        synchronized (ConnPool.class){
            if(instance==null){
                instance= new ConnPool();
                return instance;
            }

        }
        return instance;
    }


    /**
     */
    private ConnPool(){
        poolinit();
    }

    private void poolinit() {
        for (int i =0;i<MIN;i++){
            Connection conn = wapperConn();
            if(conn!=null){
                pool.add(conn);
            }
        }
    }

    private Connection wapperConn() {
        try {
            Class.forName("com.mysql.jdbc.driver");
            String url = "jdbc:mysql://localhost:3306/big12" ;
            String user = "root" ;
            String pass = "root" ;
            Connection connection = DriverManager.getConnection(url, user, pass);
            return new Conn(connection,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized Connection getConnection(){
        if (!pool.isEmpty()){

        }
    }
}


