package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//数据库工具类(获取连接）
public class DbUtil {
    private static DbUtil dbUtil = null;
    private final String DBName = "mydb";
    private final String DBURL = "jdbc:mysql://10.20.185.2:3306"+"?useSSL=false&serverTimezone=UTC";
    private final String User = "wtq";
    private final String Password = "123456";

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

    public static void main(String[] args)
    {
        DbUtil test = new DbUtil();
        Connection connection = test.getConn();

    }
}
