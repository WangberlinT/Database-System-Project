package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DruidUtil {
    private static Connection connection = null;

    public static DataSource getDatasource() {
        return DruidConnection.getInstance().getDataSource();
    }

    public static Connection getConnection() {
        return DruidConnection.getInstance().getConnection();
    }

    public void release() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
