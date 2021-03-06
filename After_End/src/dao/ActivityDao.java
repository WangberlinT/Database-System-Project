package dao;

import bean.Activity;
import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

//社团活动的增删改查
public class ActivityDao {

    //查看我参加的活动总数
    public long totalActivityByID(int uid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*) from Activity_User where User_ID=?";
            return queryRunner.query(sql, new ScalarHandler<>(), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查看某个社团活动总数
    public long totalActivityByClubID(int cid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*) from Activity_Club where Club_ID=?";
            return queryRunner.query(sql, new ScalarHandler<>(), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //查看我参加的活动总页数
//    public long totalActivityPageByID(int uid) {
//        return (totalActivityByID(uid) - 1) / pageSize + 1;
//    }

    //查看我参加的活动
    public List<Activity> queryActivityByID(int uid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select a.Activity_ID,Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State\n" +
                "from Activity a join Activity_User b\n" +
                "on a.Activity_ID = b.Activity_ID\n" +
                "where User_ID = ?\n" +
                "ORDER BY Start_Time DESC LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Activity.class), uid, start, pageSize);
    }

    //查看最近1个月所有社团活动总数
    public long totalActivityForAllInMoth() {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*)\n" +
                    "from Activity\n" +
                    "where date_sub(CURDATE(), INTERVAL 30 DAY) <= date(Start_Time) and CURDATE() >= date(Start_Time) and`range` = 1;";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Activity ActivityByID(int id) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select *\n" +
                    "from Activity\n" +
                    "where Activity_ID = ?;";
            return queryRunner.query(sql, new BeanHandler<>(Activity.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查看最近1个月所有社团活动总页数
//    public long totalActivityPageForAllInMoth() {
//        return (totalActivityForAllInMoth() - 1) / pageSize + 1;
//    }


    //查看最近1个月所有社团活动历史
    public List<Activity> queryActivityForAllInMoth(int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Activity\n" +
                "where date_sub(CURDATE(), INTERVAL 30 DAY) <= date(Start_Time) and CURDATE() >= date(Start_Time) and `range` = 1\n" +
                "ORDER BY Start_Time DESC LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Activity.class), start, pageSize);
    }


    //创建一个活动
    public void insertActivity(Activity a,int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        //String sql = "insert into Activity(Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State)\n" +
                //"values(?,?,?,?,?,?);";
        String sql ="call addActclub(?,?,?,?,?,?,?)";
        Object[] param = {a.getActivity_Name(), a.getStart_Time(), a.getEnd_Time(),
                a.getActivity_contain(),a.getResponse_ID(),cid, a.isRange()};
        queryRunner.execute(sql, param);
    }

    //查看某个社一年的活动
    public List<Activity> queryActivityByClubID(int club_id, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select a.Activity_ID,Activity_Name,Start_Time,End_Time,Response_ID,`Range`,State\n" +
                "from Activity a join Activity_Club b\n" +
                "on a.Activity_ID = b.Activity_ID\n" +
                "where Club_ID = ?\n" +
                "  and date_sub(CURDATE(), INTERVAL 1 Year) <= date(Start_Time) and CURDATE() >= date(Start_Time) \n" +
                "  and `range` = 1\n" +
                "ORDER BY Start_Time DESC LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Activity.class), club_id, start, pageSize);
    }

    //删除一个活动
    public void deleteActivity(int aid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Activity\n" +
                "where Activity_ID = ?;";
        queryRunner.update(sql, aid);
    }

    public void deleteActivity(int aid, int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Activity\n" +
                "where Activity_ID = ? and Response_ID = ?;";
        queryRunner.update(sql, aid, uid);
    }

    //查看最近一周的活动数量
    public long totalActivityForAllweek() {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*)\n" +
                    "from Activity\n" +
                    "where date_sub(CURDATE(), INTERVAL 7 DAY) <= date(Start_Time) and CURDATE() >= date(Start_Time) and`range` = 1;";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //通过活动名字查询（模糊搜索包含该名字的）
    public List<Activity> queryActivityFuzzy(String name, int currentPage, int pageSize) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String tmp = "%" + name + "%";
        int start = (currentPage - 1) * pageSize;
        String sql = "select * from Activity where Activity_Name like ? and CURDATE() >= date(Start_Time) ORDER BY Start_Time DESC LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Activity.class), tmp, start, pageSize);
    }

    public long queryActivityFuzzyNum(String name) throws SQLException {
        String tmp = "%" + name + "%";
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select count(*) from Activity where Activity_Name like ? and CURDATE() >= date(Start_Time);";
        return queryRunner.query(sql, new ScalarHandler<>(), tmp);
    }
}