package dao;

import bean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.List;

public class AdminDao {

    public void updateAdmin(Admin admin) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "update Admin\n" +
                "set Password=?\n" +
                "where Admin_ID = ?;";
        Object[] param = {admin.getPassword(),admin.getAdmin_Id()};
        queryRunner.update(sql, param);
    }

    public Admin queryAdminByID(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from Admin where Admin_ID=?";
        return queryRunner.query(sql, new BeanHandler<>(Admin.class), uid);
    }

    public List<Admin> queryAllAdmin(int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from Admin LIMIT ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(Admin.class), start, pageSize);
    }

    public void insertAdmin(Admin admin) throws SQLException
    {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "insert into Admin(Admin_ID,Password)" +
                     "values(?,?)";
        queryRunner.update(sql,admin.getAdmin_Id(),admin.getPassword());
    }

    public long getTotalAdmin()
    {
        try{
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) FROM Admin";
            return queryRunner.query(sql,new ScalarHandler<>());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
