package dao;

import bean.Activity;
import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

//社团活动的增删改查
public class ActivityDao {
    private int pageSize = 10; //每页10条数据

    //查看我参加的活动总数
    public int totalActivityByID(int uid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*) from Activity_User where User_ID=?";
            return queryRunner.query(sql, new ScalarHandler<>(), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查看我参加的活动总页数
    public int totalActivityPageByID(int uid) {
        return (totalActivityByID(uid) - 1) / pageSize + 1;
    }

    //查看我参加的活动
    public List<User> queryActivityByID(int uid, int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select a.Activity_ID,Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State\n" +
                "from Activity a join Activity_User b\n" +
                "on a.Activity_ID = b.Activity_ID\n" +
                "where User_ID = ?\n" +
                "ORDER BY Start_Time DESC LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), uid, start, pageSize);
    }

    //查看最近3个月所有社团活动总数
    public int totalActivityForAllInMoth() {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*)\n" +
                    "from Activity\n" +
                    "where date_sub(CURDATE(), INTERVAL 30 DAY) <= date(Start_Time) and `range` = 1;";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查看最近3个月所有社团活动总页数
    public int totalActivityPageForAllInMoth() {
        return (totalActivityForAllInMoth() - 1) / pageSize + 1;
    }

    //查看最近3个月所有社团活动历史
    public List<User> queryActivityForAllInMoth(int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Activity\n" +
                "where date_sub(CURDATE(), INTERVAL 30 DAY) <= date(Start_Time) and `range` = 1\n" +
                "ORDER BY Start_Time DESC LIMIT?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), start, pageSize);
    }

    //创建一个活动
    public void insertActivity(Activity a) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "insert into Activity(Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State)\n" +
                "values(?,?,?,?,?,?);";

        Object[] param = {a.getActivity_Name(), a.getStart_Time(), a.getEnd_Time(),
                a.getResponse_ID(), a.isRange(), a.isState()};
        queryRunner.update(sql, param);
    }

    //查看某个社一年的活动
    public List<User> queryActivityByClubID(int club_id, int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select a.Activity_ID,Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State\n" +
                "from Activity a join Activity_Club b\n" +
                "on a.Activity_ID = b.Activity_ID\n" +
                "where Club_ID = ?\n" +
                "  and date_sub(CURDATE(), INTERVAL 365 DAY) <= date(Start_Time)\n" +
                "  and `range` = 1\n" +
                "ORDER BY Start_Time DESC LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), club_id, start, pageSize);
    }

    //删除一个活动
    public void deleteActivity(int id) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Activity\n" +
                "where Activity_ID = ?;";
        queryRunner.update(sql, id);
    }
}
