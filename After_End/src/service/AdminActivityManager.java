package service;

import util.CustomerException;

import java.sql.SQLException;

public class AdminActivityManager extends BaseService{
    ActivityService activityService;

    public AdminActivityManager()
    {
        activityService = new ActivityService();
    }

    public void showMenu()
    {
        System.out.println("---活动管理---\n");
        System.out.print("1.查看活动\n" +
                "2.删除活动\n" +
                "3.返回\n>");
    }

    public void run()
    {
        int instruction = -1;
        final int EXIT = 3;
        while (true)
        {
            showMenu();
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //todo 查看活动
                        checkActivity();
                        break;
                    case 2:
                        //todo 删除活动
                        deleteActivity();
                        break;
                    default:
                        throw new CustomerException("输入超限");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void deleteActivity()
    {
        System.out.print("输入活动id\n>");
        int instruction;
        final int EXIT = 2;
        int id;
        try {
            id = Integer.parseInt(in.nextLine());
            System.out.print("确认删除此活动\n1.确认 2.返回\n>");
            instruction = Integer.parseInt(in.nextLine());
            if(instruction == EXIT)
                return;
            System.out.println("正在删除...");
            activityService.deleteActivityByClub(id);
            System.out.println("删除成功!");
        }
        catch (Exception e)
        {
            if(e instanceof NumberFormatException)
            {
                System.out.println("非法输入!");
            }
            else
                System.out.println("数据库连接失败");
        }
    }

    private void checkActivity()
    {
        System.out.print("1.输入活动名称(模糊搜索,输入回车查询所有)\n>");
        String name = in.nextLine();
        try {
            activityService.showActivityword(name);
        }
        catch (SQLException e)
        {
            System.out.println("数据库连接失败!");
        }
    }
}
