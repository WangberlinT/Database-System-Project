package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//数据库工具类(获取连接）
public class DbUtil {
    private final String DBName = "mydb";
    private final String DBURL = "jdbc:mysql://10.20.197.208:3306"+"?useSSL=false&serverTimezone=UTC";
    private final String User = "wtq";
    private final String Password = "123456";

    private Connection conn;

    //应用中不同类型的用户登陆后使用不同的user连接数据库
    //权限不同
    private DbUtil(String user,String password) {
        try {
            conn = DriverManager.getConnection(DBURL, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DbUtil()
    {
        try {
            conn = DriverManager.getConnection(DBURL, User, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DbUtil getInstance(String user,String password) {
        return new DbUtil(user,password);
    }

    public static DbUtil getInstance() {
        return new DbUtil();
    }

    public Connection getConn() {
        return conn;
    }

    //test
//    public static void main(String[] args)
//    {
//        DbUtil test = new DbUtil();
//        Connection connection = test.getConn();
//
//    }
}
