package dao;

import java.sql.*;

//所有dao的父类
public class BaseDao {
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement ps;
    protected CallableStatement cs;
    protected ResultSet rs;

    public Connection getCon() throws SQLException {
        connection = DbUtil.getInstance().getConn();
        return connection;
    }
    //以指定的用户连接数据库
    public Connection getCon(String user,String password) throws SQLException{
        connection = DbUtil.getInstance(user, password).getConn();
        return connection;
    }

    //更新
    //传入多个或无参数
    protected int exeUpdate(String sql, Object... objects) {
        int result = 0;
        try {
            ps = connection.prepareStatement(sql);
            // 如果传入的参数不为空，遍历，添加参数
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //查询
    //传入多个或无参数
    protected void exeQuery(String sql, Object... objects) {
        try {
            ps = connection.prepareStatement(sql);
            // 如果传入的参数不为空，遍历，添加参数
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //调用存储过程
    //传入0/多个参数
    protected void exeProcedure(String sql, Object... objects) {
        try {
            cs = connection.prepareCall(sql);
            // 如果传入的参数不为空，遍历，添加参数
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    cs.setObject(i + 1, objects[i]);
                }
            }
            cs.execute(); //执行,获取结果集在子类实现
//            rs = cs.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭所有连接
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

    public static void main(String[] args) {
        BaseDao a = new BaseDao();
        try {
            a.getCon("lyc","123456");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
