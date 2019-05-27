package view;

import java.sql.Date;
import java.sql.*;
import java.util.*;

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
        System.out.printf(

                         "\n-----------------\n"
                        + "1.我的参加的活动\n"
                        + "2.最近一个月的活动\n"
                        + "3.创建一个活动\n"
                        + "4.查看社团一年的活动\n"
                        + "5.删除活动\n"
                        + "6.退出\n"
                );

    }

    public void displayMenu() {
        int instruction;
        final int EXIT = 6;

        while (true) {
            display();
            System.out.print('>');
            try {
                instruction = Integer.parseInt(in.nextLine());
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
                        //createactivity();
                        break;
                    case 4:
//                      //todo 查看社团一年的活动
                        System.out.println("请输入社团ID");
                        int id = Integer.parseInt(in.nextLine());
                        activityYearByClub(id);
                        break;
                    case 5:
                        //todo 删除活动,只能删自己创建的活动
                        deleteMyActivity();
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

        String prompt = "----------\n" +
                "最近一月没有活动\n" +
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
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                    break;

                switch (instruction) {
                    case 1:
                        //todo 选择查看活动的详细信息
                        System.out.println("请输入活动ID");
                        int ID = Integer.parseInt(in.nextLine());
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

    private void activityJoin() throws SQLException {
        activityService.showActivityList(user.getUser_ID());
    }

   /* private void createactivity() throws SQLException {
        System.out.println("请输入活动名称");
        String name = in.nextLine();
        System.out.println("请输入活动开始时间\n"+"年：");
        int year = Integer.parseInt(in.nextLine());

        int month = Integer.parseInt(in.nextLine());

        int day = Integer.parseInt(in.nextLine());

        int hour = Integer.parseInt(in.nextLine());

        int min = Integer.parseInt(in.nextLine());

        int second = Integer.parseInt(in.nextLine());
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day, hour, min, second);
        //Date start = calendar.getTime();
        System.out.println("请输入活动结束时间");
        //Date end;
        int Response_ID = user.getUser_ID();
        System.out.println("请输入活动是否可见\n" + "输入1为可见，其他为不可见");
        int r = Integer.parseInt(in.nextLine());
        boolean range;
        if(r == 0){
            range = true;
        }else{
            range = false;
        }
        Activity a = new Activity(name,start,end,Response_ID,range,true);
        activityService.createActivity(a);
    }*/

    private void activityYearByClub(int id) throws SQLException {
        //todo activityBrowse 最近一年的活动
        activityService.showActivityYearByClub(id);
        long num = activityService.numActivityByClub(id);

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
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                    break;

                switch (instruction) {
                    case 1:
                        //todo 选择查看活动的详细信息
                        System.out.println("请输入活动ID");
                        int ID = Integer.parseInt(in.nextLine());
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

    private void deleteMyActivity() throws SQLException{
        System.out.println("请输入要删除的活动id");
        int aid  = Integer.parseInt(in.nextLine());
        activityService.deleteActivityByClub(aid,user.getUser_ID());
    }


}
