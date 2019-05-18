package dao;

import bean.Announcement;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDao {

    List<Announcement> show_Anno(int uid) {
        List<Announcement> list = new ArrayList<>();
//            getCon();
        String sql = "select a.Announcement_ID, C.Club_Name, a.Title, a.Content, a.Time, U.Name\n" +
                "from Announcement a\n" +
                "         join Club C on a.Club_ID = C.Club_ID\n" +
                "         join Work w on C.Club_ID = w.Club_ID\n" +
                "         join User U on a.Publisher = U.User_ID\n" +
                "where w.User_ID = ?;";
//            exeQuery(sql, uid);
        //只获取社团名称和发布人名字
        return list;
    }
}
