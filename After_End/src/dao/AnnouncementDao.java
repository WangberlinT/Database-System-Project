package dao;

import bean.*;

import java.sql.SQLException;

import java.util.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

//通知的增删改查
public class AnnouncementDao {

    //添加公告，并发送通知（存储过程实现）
    public void addAnnouncement(int cid, int uid, String content, String title) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addAnnouncement(?,?,?,?)";
        Object[] param = {cid, uid, title, content};
        queryRunner.execute(sql, param);
    }

    /**
     * 删除公告与通知表的对应项
     */
    public void deleteAnno(String title, int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        Announcement ano = searchByTitle(title, cid);
        String sql = "delete from Announcement where Announcement_ID=? ;";
        queryRunner.update(sql, ano.getAnnouncement_ID());
    }

    /**
     * 删除公告与通知表的对应项
     */
    public void deleteAnno(int aid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Announcement where Announcement_ID=? ;";
        queryRunner.update(sql, aid);
    }

    /**
     * 通过标题找公告
     */
    public Announcement searchByTitle(String title, int cid) throws SQLException {
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher from Announcement natural join Club "
                + "where Title=? and Club_ID=?;";
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        return queryRunner.query(sql, new BeanHandler<>(Announcement.class), title, cid);
    }

    /**
     * 改变公告内容
     * String pre is the  name of announcement before change.
     * String choose is the part you want to change.
     * input title to change tile
     * input content to change content
     */
    public void changeAnno(String pre, String choose, String after, int uid, int cid) throws SQLException {
        String sql;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        if (choose.equals("title")) {
            sql = "update Announcement set title=? where "
                    + "Title=? and Publisher=? and Club_ID=?;";
            Object[] param = {after, pre, uid, cid};
            queryRunner.update(sql, param);
        } else if (choose.equals("content")) {
            sql = "update Announcement set content=? where "
                    + "Title=? and Publisher=? and Club_ID=?;";
            Object[] param = {after, pre, uid, cid};
            queryRunner.update(sql, param);
        } else {
            System.out.println("invalid input of choose, please input title or content");
        }
    }

    /**
     * 找到用户自己发的公告
     * publish from me
     */
    public List<Announcement> checkMyAnno(int uid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher from Announcement natural join Club "
                + "where Publisher=? order by Time desc limit ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), uid, start, pageSize);
    }

    //找到用户自己发的公告总数
    public long myTotalAnno(int uid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) from Announcement natural join Club where Publisher=?";
            return queryRunner.query(sql, new ScalarHandler<>(), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询最近一个月的所有公告
     */
    public List<Announcement> checkAnnoMonth(int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher from Announcement natural join Club "
                + "where Time>=now()-000001000000 order by Time desc LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), start, pageSize);
    }

    //查询最近一个月的所有公告总数
    public long totalAnnoMonth() {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) from Announcement natural join Club where where Time>=now()-000001000000";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询社团公告
    public List<Announcement> checkAnnoClub(int cid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Club_Name,Title,Content,Time from Announcement natural join Club "
                + "where Club_ID=? order by Time desc;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), cid, start, pageSize);
    }

    //查询社团公告总数
    public long totalAnnoByClub(int cid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) from Announcement natural join Club where Club_ID=?";
            return queryRunner.query(sql, new ScalarHandler<>(), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询我的未读公告
     * announce to me
     */
    public List<Announcement> checkAnnoToMe(int uid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher "
                + "from Announcement natural join Notification natural join Club "
                + "where  User_ID=? and State=0 order by Time desc LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), uid, start, pageSize);
    }

    //查询一个用户未读公告数量
    public long unReadAnnouncement(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select COUNT(*) from Announcement natural join Notification "
                + "where User_ID=? and State=0;";
        return queryRunner.query(sql, new ScalarHandler<>());
    }

    //标记公告已读
    public void markRead(int aid, int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "update Notification set Read_Time=now() and State=1"
                + " where User_ID=? and Announcement_ID=?;";
        queryRunner.update(sql, uid, aid);
    }
}
