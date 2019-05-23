package dao;

import bean.*;


import java.sql.*;

import java.util.*;


import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;

//通知的增删改查
public class AnnouncementDao {

    //添加公告，并发送通知（存储过程实现）
    public void addAnnouncement(int cid, int uid, String content, String title) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addAnnouncement(?,?,?,?)";
        Object[] param = {cid, uid, title, content};
        queryRunner.execute(sql, param);
    }

    public void deleteAnno(String title, int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Notification where Announcement_ID = ?";
        Announcement ano = searchbyTitle(title, cid);
        Object[] param = {ano.getAnnouncement_ID()};
        queryRunner.update(sql, param);
        sql = "delete from Announcement where Announcement_ID=? ;";
        queryRunner.update(sql, param);

    }

    public void deleteAnno(int aid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Notification where Announcement_ID = ?";
        Object[] param = {aid};
        queryRunner.update(sql, param);
        sql = "delete from Announcement where Announcement_ID=? ;";
        queryRunner.update(sql, param);
    }

    public Announcement searchbyTitle(String title, int cid) throws SQLException {
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher from Announcement natural join Club "
                + "where Title=? and Club_ID=?;";

        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        return queryRunner.query(sql, new BeanHandler<>(Announcement.class), title, cid);

    }

    /**
     * String pre is the  name of announcement before change.
     * String choose is the part you want to change.
     * input title to change tile
     * input content to change content
     *
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
     * publish from me
     */
    public List<Announcement> checkmyAnno(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher from Announcement natural join Club "
                + "where Publisher=? order by Time desc;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), uid);

    }

    public List<Announcement> checkAnnoMonth() throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher from Announcement natural join Club "
                + "where Time>=now()-000001000000 order by Time desc;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class));
    }

    public List<Announcement> checkClubAnno(int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Club_Name,Title,Content,Time from Announcement natural join Club "
                + "where Club_ID=? order by Time desc;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), cid);
    }

    /**
     * announce to me
     *
     */
    public List<Announcement> checkAnnoTome(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Announcement_ID,Content,Time,Title,Club_ID,Club_Name club,Publisher "
                + "from Announcement natural join Notification natural join Club "
                + "where  User_ID=? and State=0 order by Time desc;";
        return queryRunner.query(sql, new BeanListHandler<>(Announcement.class), uid);
    }

    public long noReadAnnoNum(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select COUNT(*) from Announcement natural join Notification "
                + "where User_ID=? and State=0;";
        return queryRunner.query(sql, new ScalarHandler<>());
    }

    public void markread(int aid, int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "update Notification set Read_Time=now() and State=1"
                + " where User_ID=? and Announcement_ID=?;";
        Object[] param = {uid, aid};
        queryRunner.update(sql, param);
    }
}
