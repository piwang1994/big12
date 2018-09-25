package mypool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn extends ConnAdapter {
    public Connection rawconn;
    public ConnPool pool;

    public Conn(Connection connection, ConnPool pool) {
        this.pool=pool;
        this.rawconn=connection;
    }


    @Override
    public Statement createStatement() throws SQLException {
        return rawconn.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return rawconn.prepareStatement(sql);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return rawconn.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        rawconn.commit();
    }

    @Override
    public void rollback() throws SQLException {
        rawconn.rollback();
    }

    @Override
    public void close() throws SQLException {
        //TODO 重写 close方法
    }
}
