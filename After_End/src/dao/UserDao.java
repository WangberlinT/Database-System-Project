package dao;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

//学生用户的增删改查
public class UserDao {

    //查询所有用户
    public List<User> queryAllUser() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDatasource());
        String sql = "select * from User";
        return queryRunner.query(sql, new BeanListHandler<>(User.class));

    }

    //通过uid查询用户
    public User queryUserByID(int uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDatasource());
        String sql = "select * from User where User_ID=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), uid);
    }

    //通过名字查询用户
    public User queryUserByName(String Name) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDatasource());
        String sql = "select * from User where Name=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), Name);
    }

    //更新用户信息
    public void updateUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDatasource());
        String sql = "update User\n" +
                "set Password=?,\n" +
                "    Name=?,\n" +
                "    Sex=?,\n" +
                "    Born=?,\n" +
                "    Major=?,\n" +
                "    Address=?,\n" +
                "    Phone_Number=?,\n" +
                "    Born_Access=?,\n" +
                "    Address_Access=?,\n" +
                "    Phone_Number_Access=?\n" +
                "where User_ID = ?;";
        Object[] param = {user.getPassword(), user.getName(), user.getSex(), user.getBorn(),
                user.getMajor(), user.getAddress(), user.getPhone_Number(), user.isBorn_access(),
                user.isAddress_Access(), user.isPhone_Access(), user.getUser_ID()};
        queryRunner.update(sql, param);
    }

    //注册用户
    public void insertUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDatasource());
        String sql = "insert into User(user_id, password, name, sex, born, major, address, phone_number,\n" +
                "                 Born_Access,Address_Access,Phone_Number_Access)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";
        Object[] param = {user.getUser_ID(), user.getPassword(), user.getName(), user.getSex(), user.getBorn(),
                user.getMajor(), user.getAddress(), user.getPhone_Number(), user.isBorn_access(),
                user.isAddress_Access(), user.isPhone_Access()};
        queryRunner.update(sql, param);
    }

}
