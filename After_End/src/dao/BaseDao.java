package dao;

import java.sql.*;

//所有dao的父类
public class BaseDao {
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public Connection getCon() throws SQLException {
        connection = DbUtil.getInstance().getConn();
        return connection;
    }

    //更新
//    protected int exeUpdate(String sql){}

    //查询
//    protected void  exeQuery(String sql){}

    public void closeAll() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭失败！");
        }
    }
}
