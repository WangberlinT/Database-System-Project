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
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDatasource());
        String sql = "select * from User";
        return queryRunner.query(sql, new BeanListHandler<>(User.class));

    }

    //通过uid查询用户
    public User queryUserByID(int uid) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtil.getDatasource());
        String sql = "select * from User where User_ID=?";
        return qr.query(sql, new BeanHandler<>(User.class), uid);
    }

    public void updateUser(User user) throws SQLException {
        //更新用户信息
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDatasource());
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

    //test
    public static void main(String[] args) throws SQLException {
        UserDao u1 = new UserDao();
        User tmp = u1.queryUserByID(11410101);
        tmp.setSex("女");
        u1.updateUser(tmp);
        List<User> userList = u1.queryAllUser();
        for (User u : userList) {
            System.out.println(u.getName() + "  " + u.getPhone_Number() + "  " + u.getBorn());
        }
    }
}
