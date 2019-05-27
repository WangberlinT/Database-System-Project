package dao;

import bean.User;
import bean.Club;
import bean.User_Club;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

//学生用户的增删改查
public class UserDao {

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

    //查询一个社团里面的的人
    public List<User> queryclubUser(Club club) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner(); //换了新的QR获取
        String sql = "select * from User_Club where Club_ID=? LIMIT ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), club.getClub_ID());
    }


    //查询所有用户(每页10条)
    public List<User> queryAllUser(int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
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
    public List<User> queryUserByName(String Name, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from User where Name=? LIMIT ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), Name, start, pageSize);
    }

    //获取上述查询的总条目
    public long getTotalUserByName(String Name) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) from User where Name=?";
            return queryRunner.query(sql, new ScalarHandler<>(), Name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //通过社团id查询用户
    public List<User_Club> queryUserByClub(int cid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select u.User_ID, u.Name, C.Club_ID, Club_Name, Work_Name\n" +
                "from User u\n" +
                "         join User_Club UC on u.User_ID = UC.User_ID\n" +
                "         join Club C on UC.Club_ID = C.Club_ID\n" +
                "where C.Club_ID = ?\n" +
                "limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(User_Club.class), cid, start, pageSize);
    }

    //获取上述查询的总条目
    public long getTotalUserByClub(int cid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*)\n" +
                    "from User u\n" +
                    "         join User_Club UC on u.User_ID = UC.User_ID\n" +
                    "         join Club C on UC.Club_ID = C.Club_ID\n" +
                    "where C.Club_ID = ?";
            return queryRunner.query(sql, new ScalarHandler<>(), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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