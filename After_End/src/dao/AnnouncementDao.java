package dao;

import bean.Announcement;
import bean.Club;
import bean.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnouncementDao extends BaseDao {

    List<Announcement> show_Anno(int uid) {
        List<Announcement> list = new ArrayList<>();
        try {
            getCon();
            String sql = "select a.Announcement_ID, C.Club_Name, a.Title, a.Content, a.Time, U.Name\n" +
                    "from Announcement a\n" +
                    "         join Club C on a.Club_ID = C.Club_ID\n" +
                    "         join Work w on C.Club_ID = w.Club_ID\n" +
                    "         join User U on a.Publisher = U.User_ID\n" +
                    "where w.User_ID = ?;";
            exeQuery(sql, uid);
            //只获取社团名称和发布人名字
            while (rs.next()) {
                Announcement ann = new Announcement();
                User publisher = new User();
                Club club = new Club();
                ann.setAnnouncementId(rs.getInt(1));
                club.setClubName(rs.getString(2));
                ann.setClub(club);
                ann.setTitle(rs.getString(3));
                ann.setContent(rs.getString(4));
                Timestamp time = rs.getTimestamp(5);
                ann.setTime(new Date(time.getTime()));
                publisher.setName(rs.getString(6));
                ann.setPubliser(publisher);
                list.add(ann);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
