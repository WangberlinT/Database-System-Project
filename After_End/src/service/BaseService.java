package service;

import bean.User;
import dao.*;
import util.FormatUtil;
import util.StringAlign;

import java.util.List;
import java.util.Scanner;

abstract class BaseService {
    //Util
    public static final int MAXCHAR = 20;
    static Scanner in=new Scanner(System.in);
    static FormatUtil fu = new FormatUtil();
    static StringAlign formatter = new StringAlign(MAXCHAR, StringAlign.JUST_LEFT);

    //DAO
    static ActivityDao activityDao = new ActivityDao();
    static AnnouncementDao announcementDao = new AnnouncementDao();
    static ApplyDao applyDao = new ApplyDao();
    static ClubDao clubDao = new ClubDao();
    static EvaluationDao evaluationDao = new EvaluationDao();
    static ItemDao itemDao = new ItemDao();
    static UserDao userDao = new UserDao();
    static AdminDao adminDao = new AdminDao();

    //Constant
    int pageSize = 10;  //每页10条数据

    //输出List<？> 一页的内容
    int PrintPage(int page, long totalPage, String format_head, List<?> list) {
        //format_head是已经格式化后的标题
        System.out.println(format_head);
        int No = (page - 1) * pageSize + 1;
        for (Object u : list) {
            formatter.setMaxChars(5);
            String content = formatter.format(Integer.toString(No))+u.toString();
            System.out.println(content);
            No++;
        }
        System.out.printf("当前页数/总页数:  %s/%s\n", page, totalPage);
        System.out.println("\n请输入指令：1. 上一页   2. 下一页   其他返回");
        int input = in.nextInt();
        switch (input) {
            case 1:
                page--;
                break;
            case 2:
                page++;
                break;
            default:
                page = 0;
        }
        return page;
    }

    //判断查询是否有结果
    boolean queryNotValid(long total) {
        if (total == 0) {
            System.out.println("没有查询到结果");
            return true;
        } else {
            System.out.printf("总共查询到%s条结果\n", total);
            return false;
        }
    }
}
