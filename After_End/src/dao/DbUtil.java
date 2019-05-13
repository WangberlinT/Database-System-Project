package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//数据库工具类(获取连接）
public class DbUtil {
    private static DbUtil dbUtil = null;
    private final String DBName = "";
    private final String DBURL = "";
    private final String User = "";
    private final String Password = "";

    private Connection conn;

    private DbUtil() {
        try {
            conn = DriverManager.getConnection(DBURL, User, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DbUtil getInstance() {
        if (dbUtil == null)
            dbUtil = new DbUtil();
        return dbUtil;
    }

    public Connection getConn() {
        return conn;
    }


}
