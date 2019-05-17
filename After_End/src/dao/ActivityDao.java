package dao;

import bean.Activity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//社团活动的增删改查
public class ActivityDao {

    //查询用户参加的活动，返回List
    List<Activity> Show_Act_By_uid(int uid) {
        List<Activity> list = new ArrayList<>();
            String sql = "select ac.*\n" +
                    "    from Activity_User au\n" +
                    "             join Activity ac on au.Activity_ID = ac.Activity_ID\n" +
                    "    where au.User_ID = uid order by ac.Start_Time;";
        return list;
    }
}
