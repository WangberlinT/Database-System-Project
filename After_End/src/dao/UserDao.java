package dao;

import bean.User;
import bean.Club;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

//学生用户的增删改查
public class UserDao {
    private int pageSize = 10; //每页20条数据
    private long totalUser = getTotalUser();
    private long totalPage = getTotalPage();

    //获取总用户数量
    public long getTotalUser() {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) from User";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取总页数
    public long getTotalPage() {
        return (totalUser - 1) / pageSize + 1;
    }

    //查询一个社团里面的的人
    public List<User> queryclubUser(Club club,int currentPage) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner(); //换了新的QR获取
        String sql = "select * from User_Club where Club_ID=? LIMIT ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class),club.getClub_ID());
    }


    //查询所有用户(每页10条)
    public List<User> queryAllUser(int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner(); //换了新的QR获取
        String sql = "select * from User LIMIT ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), start, pageSize);
    }

    //通过uid查询用户
    public User queryUserByID(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from User where User_ID=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), uid);
    }

    //通过名字查询用户
    public User queryUserByName(String Name) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from User where Name=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), Name);
    }

    //更新用户信息
    public void updateUser(User user) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
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
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "insert into User(user_id, password, name, sex, born, major, address, phone_number,\n" +
                "                 Born_Access,Address_Access,Phone_Number_Access)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";
        Object[] param = {user.getUser_ID(), user.getPassword(), user.getName(), user.getSex(), user.getBorn(),
                user.getMajor(), user.getAddress(), user.getPhone_Number(), user.isBorn_access(),
                user.isAddress_Access(), user.isPhone_Access()};
        queryRunner.update(sql, param);
    }
}
