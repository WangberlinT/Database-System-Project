package view;

import java.sql.SQLException;
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
                        break;
                    case 2:
                        //todo 最近一个月的活动
                        break;
                    case 3:
                        //todo 创建一个活动
                        break;
                    case 4:
//                      //todo 查看社团一年的活动
                        break;
                    case 5:
                        //todo 删除活动
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
        long num = activityService.numActivityMonth();

        String prompt = "----------\n" +
                "最近一月没有活动\n" +
                "----------\n";
        String content = "1.选择查看详细信息\n" +
                "2.上一页\n" +
                "3.下一页\n" +
                "4.返回\n>";
        int instruction = -1;
        final int EXIT = 4;
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
                        break;
                    case 2:
                        //todo 上一页
                        break;
                    case 3:
                        //todo 下一页
                        break;
                    default:
                        throw new CustomerException("输入超出范围");
                }
            } catch (Exception e) {
                //todo 异常处理
            }
        }
    }


}
