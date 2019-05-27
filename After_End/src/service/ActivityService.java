package service;

import java.sql.SQLException;
import java.util.List;


import bean.*;
import dao.*;



public class ActivityService extends BaseService{
    private String ActivityHead = "活动ID     名称      开始时间        结束时间        负责人";

    //查看我参加的活动总数
    public void activityNumberTotal(int uid) throws SQLException{
        long num = activityDao.totalActivityByID(uid);
        System.out.println("你一共参加了"+num+"个活动");
    }


    //查看我参加的活动
    public void showActivityList(int uid){
        long total = activityDao.totalActivityByID(uid);
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = ActivityDao.queryActivityByID(uid,page,pageSize);
            page = PrintPage(page, totalPage, ActivityHead, ul);
            if (page == 0) return;
        }
    }



    //查看最近1个月所有社团活动历史
    public void showActivityMonth(int uid){
        long total = activityDao.totalActivityForAllInMoth();
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = ActivityDao.queryActivityForAllInMoth(page,pageSize);
            page = PrintPage(page, totalPage, ActivityHead, ul);
            if (page == 0) return;
        }
    }

    //创建一个活动
    public void createActivity(Activity a){
        System.out.println("正在创建活动");
        ActivityDao.insertActivity(a);
        System.out.println("活动已经成功创建");
    }

    //查看某个社一年的活动
    public void showActivityYearByClub(int id){
        long total = activityDao.totalActivityByClubID(id);
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = ActivityDao.queryActivityByClubID(id,page,pageSize);
            page = PrintPage(page, totalPage, ActivityHead, ul);
            if (page == 0) return;
        }
    }


    //删除一个活动，管理员使用
    public void deleteActivityByClub(int id){
        System.out.println("正在删除活动");
        ActivityDao.deleteActivity(id);
        System.out.println("活动已经成功删除");
    }

    //查看最近一周的活动数量
}
