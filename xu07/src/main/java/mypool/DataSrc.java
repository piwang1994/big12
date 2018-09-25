package mypool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSrc extends DataSourceAadapter {
    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }
}
