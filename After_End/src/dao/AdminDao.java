package dao;

import bean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

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

//    public static void main(String[] args) {
//        AdminDao adminDao = new AdminDao();
//        try {
//            adminDao.queryAdminByID(110);
//            Admin admin = new Admin(110,"admin123");
//            adminDao.updateAdmin(admin);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
