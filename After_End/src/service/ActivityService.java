package service;

import java.sql.SQLException;
import java.util.List;


import bean.*;

public class ActivityService extends BaseService {
    private String ActivityHead = "     活动ID     名称         开始时间                    结束时间                    负责人         是否过期";

    //查看我参加的活动总数
    public void activityNumberTotal(int uid) throws SQLException {
        long num = activityDao.totalActivityByID(uid);
        System.out.println("你一共参加了" + num + "个活动");
    }


    //查看我参加的活动
    public void showActivityList(int uid) throws SQLException {
        long total = activityDao.totalActivityByID(uid);
        int page = 1;
        if (queryNotValid(total)) return;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> l = activityDao.queryActivityByID(uid, page, pageSize);
            page = PrintPage(page, totalPage, ActivityHead, l);
            if (page == 0) return;
        }
    }

    //返回活动的负责人
    public int activityResponse(int id) throws SQLException {
        Activity a = activityDao.ActivityByID(id);
        int respon = a.getResponse_ID();
        return respon;
    }

    //查看某个活动 todo
    public void showActivityByID(int id) throws SQLException {
        Activity a = activityDao.ActivityByID(id);
        if (a == null) {
            System.out.println("无效的ID");
        } else {
            a.Print();
        }
    }

    //查看最近一个月所有社团活动数量
    public long numActivityMonth() throws SQLException {
        return activityDao.totalActivityForAllInMoth();
    }

    //查看某个社团一年的活动数量
    public long numActivityByClub(int id) throws SQLException {
        return activityDao.totalActivityByClubID(id);
    }

    //模糊查询
    public long numActivityByword(String word) throws SQLException {
        long total = activityDao.queryActivityFuzzyNum(word);
        return total;
    }

    public void showActivityword(String word) throws SQLException {
        long total = activityDao.queryActivityFuzzyNum(word);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = activityDao.queryActivityFuzzy(word, page, pageSize);
            page = PrintPage(page, totalPage, ActivityHead, ul);
            if (page == 0) return;
        }
    }

    //查看最近1个月所有社团活动历史
    public void showActivityMonth() throws SQLException {
        long total = activityDao.totalActivityForAllInMoth();
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = activityDao.queryActivityForAllInMoth(page, pageSize);
            page = PrintPage(page, totalPage, ActivityHead, ul);
            if (page == 0) return;
        }
    }

    //创建一个活动
    public void createActivity(Activity a, int cid) throws SQLException {
        System.out.println("正在创建活动");
        activityDao.insertActivity(a,cid);
        System.out.println("活动已经成功创建");
    }

    //查看某个社一年的活动
    public void showActivityYearByClub(int id) throws SQLException {
        long total = activityDao.totalActivityByClubID(id);
        int page = 1;
        if (queryNotValid(total)) return;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = activityDao.queryActivityByClubID(id, page, pageSize);
            page = PrintPage(page, totalPage, ActivityHead, ul);
            if (page == 0) return;
        }
    }


    //删除一个活动，管理员使用
    public void deleteActivityByClub(int id) throws SQLException {
        System.out.println("正在删除活动");
        activityDao.deleteActivity(id);
        System.out.println("活动已经成功删除");
    }

    //删除一个活动，用户使用
    public void deleteActivityByClub(int aid, int uid) throws SQLException {
        System.out.println("正在删除活动");
        activityDao.deleteActivity(aid, uid);
        System.out.println("活动已经成功删除");
    }
    //查看最近一周的活动数量
}
