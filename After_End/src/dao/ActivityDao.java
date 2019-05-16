package dao;

import bean.Activity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//社团活动的增删改查
public class ActivityDao extends BaseDao {

    //查询用户参加的活动，返回List
    List<Activity> Show_Act_By_uid(int uid) {
        List<Activity> list = new ArrayList<>();
        try {
            getCon();
            String sql = "select ac.*\n" +
                    "    from Activity_User au\n" +
                    "             join Activity ac on au.Activity_ID = ac.Activity_ID\n" +
                    "    where au.User_ID = uid order by ac.Start_Time;";
            exeQuery(sql, uid);
            while (rs.next()) {
                Activity act = new Activity();
                act.setActivityID(rs.getInt(1));
                act.setActivityName(rs.getString(2));
                Timestamp start = rs.getTimestamp(3);
                Timestamp end = rs.getTimestamp(4);
                //不确定这样的获取时间行不行
                act.setStartTime(new Date(start.getTime()));
                act.setEndTime(new Date(end.getTime()));
                act.setApproverID(rs.getInt(5));
                act.setResponseID(rs.getInt(6));
                act.setIsPublic(rs.getInt(7));
                act.setState(rs.getBoolean(8));
                list.add(act);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
