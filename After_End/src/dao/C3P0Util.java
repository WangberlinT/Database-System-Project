package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0Util {
    private static ComboPooledDataSource cpds;

    /*
     * 它为null表示没有事务
     * 它不为null表示有事务
     * 当开启事务时，需要给它赋值
     * 当结束事务时，需要给它赋值为null
     * 并且在开启事务时，让dao的多个方法共享这个Connection
     */

    static {
        Properties p = new Properties(System.getProperties());
        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF"); // Off or any other level
        System.setProperties(p);
        cpds = new ComboPooledDataSource();
    }

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    public static DataSource getDatasource() {
        return cpds;
    }

    public static Connection getConnection() {
        try {
            Connection con = tl.get();
            if (con != null) return con;  // 如果有事务，返回当前事务的con
            return cpds.getConnection(); //如果没有事务，通过连接池返回新的con
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
            return null;
        }
    }

    //开启事务
    public static void beginTransaction() throws SQLException {
        Connection con = tl.get();//获取当前线程的事务连接
        if (con != null) throw new SQLException("已经开启了事务，不能重复开启！");
        con = cpds.getConnection();//给con赋值，表示开启了事务
        con.setAutoCommit(false);//设置为手动提交
        tl.set(con);//把当前事务连接放到tl中
    }

    //  提交事务
    public static void commitTransaction() throws SQLException {
        Connection con = tl.get();//获取当前线程的事务连接
        if (con == null) throw new SQLException("没有事务不能提交！");
        con.commit();//提交事务
        con.close();//关闭连接
        con = null;//表示事务结束！
        tl.remove();
    }

    // 回滚事务
    public static void rollbackTransaction() throws SQLException {
        Connection con = tl.get();//获取当前线程的事务连接
        if (con == null) throw new SQLException("没有事务不能回滚！");
        con.rollback();
        con.close();
        con = null;
        tl.remove();
    }

    //释放连接
    public static void release(Connection connection) {
        try {
            Connection con = tl.get();  //获取当前线程的事务连接
            if (connection != con) {    //如果参数连接，与当前事务连接不同，说明这个连接不是当前事务，可以关闭！
                if (connection != null && !connection.isClosed())  //如果参数连接没有关闭, 关它
                    connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}