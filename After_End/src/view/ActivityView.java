package view;

import java.sql.*;
import java.time.LocalDateTime;

import bean.*;
import service.*;
import util.*;

public class ActivityView extends View {
    private User user;
    private static ActivityService activityService;

    ActivityView(User user) {
        this.user = user;
        activityService = new ActivityService();
    }

    private void display() {
        System.out.print(
                "\n-----------------\n"
                        + "1.我的参加的活动\n"
                        + "2.最近一个月的活动\n"
                        + "3.创建一个活动\n"
                        + "4.查看社团一年的活动\n"
                        + "5.删除活动\n"
                        + "6.模糊查询\n"
                        + "7.退出\n"
        );
    }

    public void displayMenu() {
        int instruction;
        final int EXIT = 7;

        while (true) {
            display();
            System.out.print('>');
            try {
                instruction = InputInt(in);
                if (instruction == EXIT)
                    break;
                switch (instruction) {
                    case 1:
                        //todo 我的参加的活动
                        activityJoin();
                        break;
                    case 2:
                        //todo 最近一个月的活动
                        activityMonthBrowse();
                        break;
                    case 3:
                        //todo 创建一个活动
                        createActivity();
                        break;
                    case 4:
                        //todo 查看社团一年的活动
                        System.out.println("请输入社团ID");
                        int id = InputInt(in);
                        activityYearByClub(id);
                        break;
                    case 5:
                        //todo 删除活动,只能删自己创建的活动
                        deleteMyActivity();
                        break;
                    case 6:
                        //todo 模糊查询
                        System.out.println("请输入关键词");
                        String word = in.next();
                        activitySearch(word);
                        break;
                    default:
                        throw new CustomerException("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("无效输入！");
            }
        }
    }

    private void activityMonthBrowse() throws SQLException {
        //todo activityBrowse 最近一个个月的活动
        activityService.showActivityMonth();
        long num = activityService.numActivityMonth();
        detail(num);
    }
    private void activitySearch(String word) throws SQLException{
        activityService.showActivityword(word);
        long num = activityService.numActivityByword(word);
        detail(num);
    }
    private void activityJoin() throws SQLException {
        activityService.showActivityList(user.getUser_ID());
    }

    private void createActivity() throws SQLException {
        System.out.println("请输入活动名称");
        String name = in.next();
        System.out.println("请输入活动内容");
        String content = in.next();
        System.out.println("请输入活动开始时间\n" + "年：");
        int year = InputInt(in);
        System.out.println("请输入活动开始时间\n" + "月：");
        int month = InputInt(in);
        System.out.println("请输入活动开始时间\n" + "日：");
        int day = InputInt(in);
        System.out.println("请输入活动开始时间\n" + "时：");
        int hour = InputInt(in);
        System.out.println("请输入活动开始时间\n" + "分：");
        int min = InputInt(in);
        int second = 0;
        Timestamp start = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, min, second));
        System.out.println("请输入活动结束时间\n" + "年：");
        year = InputInt(in);
        System.out.println("请输入活动结束时间\n" + "月：");
        month = InputInt(in);
        System.out.println("请输入活动结束时间\n" + "日：");
        day = InputInt(in);
        System.out.println("请输入活动结束时间\n" + "时：");
        hour = InputInt(in);
        System.out.println("请输入活动结束时间\n" + "分：");
        min = InputInt(in);
        Timestamp end = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, min, second));
        int Response_ID = user.getUser_ID();
        System.out.println("请输入活动是否可见\n" + "输入1为可见，其他为不可见");
        int r = InputInt(in);
        boolean range;
        range = (r == 0);

        Activity a = new Activity(name, content, start, end, Response_ID, range, true);
        activityService.createActivity(a);
    }

    private void activityYearByClub(int id) throws SQLException {
        //todo activityBrowse 最近一年的活动
        activityService.showActivityYearByClub(id);
        long num = activityService.numActivityByClub(id);
        detail(num);
    }
    // 详细查询
    private void detail(long num){
        String prompt = "----------\n" +
                "此社团没有活动\n" +
                "----------\n";
        String content = "1.选择查看详细信息\n" +
                "2.返回\n>";
        int instruction = -1;
        final int EXIT = 2;
        while (true) {
            //todo 获取所有活动
            if (num == 0)//如果没有活动
                System.out.print(prompt);
            System.out.print(content);

            try {
                instruction = InputInt(in);

                if (instruction == EXIT)
                    break;

                switch (instruction) {
                    case 1:
                        //todo 选择查看活动的详细信息
                        System.out.println("请输入活动ID");
                        int ID = InputInt(in);
                        activityService.showActivityByID(ID);
                        break;
                    default:
                        throw new CustomerException("输入超出范围");
                }
            } catch (Exception e) {
                //todo 异常处理
            }
        }
    }

    private void deleteMyActivity() throws SQLException {
        System.out.println("请输入要删除的活动id");
        int aid = InputInt(in);
        int respon = activityService.activityResponse(aid);
        if(user.getUser_ID() == respon)
            activityService.deleteActivityByClub(aid, user.getUser_ID());
        else{
            System.out.println("您无权删除此活动");
        }
    }
}
