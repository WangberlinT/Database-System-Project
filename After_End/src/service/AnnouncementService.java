package service;

import bean.Announcement;

import java.sql.SQLException;
import java.util.List;

public class AnnouncementService extends BaseService {
    private String head = "来自社团   标题   内容   时间";

    //添加公告，并发送通知
    public void addAnnouncement(int cid, int uid, String content, String title) throws SQLException {
        announcementDao.addAnnouncement(cid, uid, title, content);
    }

    //删除公告与通知表的对应项,通过标题和ClubID
    public void deleteAnno(String title, int cid) throws SQLException {
        announcementDao.deleteAnno(title, cid);
    }

    //删除公告与通知表的对应项,通过aid
    public void deleteAnno(int aid) throws SQLException {
        announcementDao.deleteAnno(aid);
    }

    //通过标题查公告
    public void searchAnno(String title, int cid) throws SQLException {
        Announcement ann = announcementDao.searchByTitle(title, cid);
        System.out.println(head);
        System.out.println(ann.toString());
    }

    //改变公告内容
    public void changeAnno(String pre, String choose, String after, int uid, int cid) throws SQLException {
        announcementDao.changeAnno(pre, choose, after, uid, cid);
    }

    //找到用户自己发的公告
    public void checkMyAnno(int uid) throws SQLException {
        long total = announcementDao.myTotalAnno(uid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Announcement> list = announcementDao.checkMyAnno(uid, page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //查询最近一个月的所有公告
    public void checkAnnoMonth() throws SQLException {
        long total = announcementDao.totalAnnoMonth();
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Announcement> list = announcementDao.checkAnnoMonth(page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //查询社团公告
    public void checkAnnoClub(int cid) throws SQLException {
        long total = announcementDao.totalAnnoByClub(cid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Announcement> list = announcementDao.checkAnnoClub(cid, page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //查询我的未读公告并标记为已读
    public void checkAnnoToMe(int uid) {
        try {
            long total = announcementDao.unReadAnnouncement(uid);
            if (queryNotValid(total)) return;
            int page = 1;
            long totalPage = (total - 1) / pageSize + 1;
            while (page <= totalPage) {
                List<Announcement> list = announcementDao.checkAnnoToMe(uid, page, pageSize);
                for (int i = 0; i < pageSize; i++) {
                    announcementDao.markRead(list.get(i).getAnnouncement_ID(), uid);
                }
                page = PrintPage(page, totalPage, head, list);
                if (page == 0) return;
            }
        } catch (SQLException e) {
            System.out.println("查询我的未读公告失败！");
        }
    }
}
