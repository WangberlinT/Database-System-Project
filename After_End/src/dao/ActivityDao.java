package dao;

import bean.Activity;
import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;

//社团活动的增删改查
public class ActivityDao {

    //查看我参加的活动
    public List<User> queryActivityByID(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select a.Activity_ID,Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State\n" +
                "from Activity a join Activity_User b\n" +
                "on a.Activity_ID = b.Activity_ID\n" +
                "where User_ID = ?\n" +
                "ORDER BY Start_Time DESC;";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), uid);
    }


    //查看最近3个月所有社团活动历史
    public List<User> queryActivityForAllInMoth() throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Activity\n" +
                "where date_sub(CURDATE(), INTERVAL 30 DAY) <= date(Start_Time) and `range` = 1\n" +
                "ORDER BY Start_Time DESC;";
        return queryRunner.query(sql, new BeanListHandler<>(User.class));
    }


    //创建一个活动
    public void insertActivity(Activity a) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "insert into Activity(Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State)\n" +
                "values(?,?,?,?,?,?);";

        Object[] param = {a.getActivity_Name(),a.getStart_Time(),a.getEnd_Time(),
                a.getResponse_ID(),a.isRange(),a.isState()};
        queryRunner.update(sql, param);
    }


    //查看某个社一年的活动
    public List<User> queryActivityByClubID(int club_id) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select a.Activity_ID,Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State\n" +
                "from Activity a join Activity_Club b\n" +
                "on a.Activity_ID = b.Activity_ID\n" +
                "where Club_ID = ?\n" +
                "  and date_sub(CURDATE(), INTERVAL 365 DAY) <= date(Start_Time)\n" +
                "  and `range` = 1\n" +
                "ORDER BY Start_Time DESC;";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), club_id);
    }

    //删除一个活动
    public void deleteActivity(int id) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Activity\n" +
                "where Activity_ID = ?;";
        queryRunner.update(sql,id);
    }

}
