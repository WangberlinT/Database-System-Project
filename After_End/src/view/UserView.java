package view;
import bean.*;
import dao.UserDao;
import service.UserService;
import util.CustomerException;
import util.FormatUtil;
import util.StringAlign;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserView extends View{
    private User user;
    private UserDao userDao;
    private UserService userService;
    private int News;
    private boolean hasNewActivities;
    private ClubView clubView;

    public UserView(User user)
    {
        this.user = user;
        this.userDao = new UserDao();
        userService = new UserService(user);
        clubView = new ClubView();
        News = 0;
        hasNewActivities = false;
    }

    public void displayMenu()
    {
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
                        informationCheck();
                        break;
                    case 2:
                        //todo 我的社团
                        clubView.myClub();
                        break;
                    case 3:
                        //todo 社团浏览
                        clubView.clubBrowse();
                        break;
                    case 4:
                        //新活动已读
                        hasNewActivities = false;
                        activityBrowse();
                        //todo 活动墙浏览
                        break;
                    case 5:
                        //新消息已读
                        News = 0;
                        //todo 消息查看
                        newsBrowse();
                    default:
                        throw new CustomerException("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("无效输入！");
            }
        }
    }

    private void newsBrowse()
    {
        String prompt = "----------\n" +
                        "当前没有消息\n" +
                        "----------\n";
        String content = "1.选择查看详细信息\n" +
                        "2.上一页\n" +
                        "3.下一页\n" +
                        "4.搜索\n" +
                        "5.过滤器\n" +
                        "6.返回\n>";
        int instruction = -1;
        final int EXIT = 6;
        while(true)
        {
            //todo 获取该用户的全部通知
            if(true)//如果没有通知
                System.out.print(prompt);
            System.out.print(content);
            try {
                instruction = Integer.parseInt(in.nextLine());
                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //todo 查看详细信息
                        break;
                    case 2:
                        //todo 上一页
                        break;
                    case 3:
                        //todo 下一页
                        break;
                    case 4:
                        //todo 搜索
                        break;
                    case 5:
                        //todo 过滤器
                        break;
                    default:
                        throw new CustomerException("输入超出范围");
                }
            }
            catch (Exception e)
            {

            }

        }
    }

    private void activityBrowse()
    {
        //todo activityBrowse
       String prompt = "----------\n" +
                       "当前没有活动\n" +
                       "----------\n";
       String content = "1.选择查看详细信息\n" +
                        "2.上一页\n" +
                        "3.下一页\n" +
                        "4.搜索\n" +
                        "5.过滤器\n" +
                        "6.我的活动" +
                        "7.返回\n>";
       int instruction = -1;
       final int EXIT = 7;
       while (true)
       {
           //todo 获取所有活动
           if(true)//如果没有活动
               System.out.print(prompt);
           System.out.print(content);

           try {
               instruction = Integer.parseInt(in.nextLine());

               if(instruction == EXIT)
                   break;

               switch (instruction)
               {
                   case 1:
                       //todo 选择查看活动的详细信息
                       break;
                   case 2:
                       //todo 上一页
                       break;
                   case 3:
                       //todo 下一页
                       break;
                   case 4:
                       //todo 模糊搜索
                       break;
                   case 5:
                       //todo 过滤器
                       break;
                   case 6:
                       //todo 我的活动
                       break;
                   default:
                       throw new CustomerException("输入超出范围");
               }
           }
           catch (Exception e)
           {
                //todo 异常处理
           }
       }
    }

    public void informationCheck()
    {
        int instruction;
        final int EXIT = 4;
        while (true) {
            //display
            String name = "<1>姓名: " + user.getName();
            String gender = "<2>性别: " + user.getSex();
            String born = "<3>生日: " + fu.formatDate(user.getBorn());
            String Major = "<4>专业: " + user.getMajor();
            String Addess = "<5>地址: " + user.getAddress();
            String Phone = "<6>电话号码: " + user.getPhone_Number();

            formatter = new StringAlign(20, StringAlign.JUST_LEFT);
            System.out.print(formatter.format(name) + "\n"
                    + formatter.format(gender) + "\n"
                    + formatter.format(born) + "\n"
                    + formatter.format(Major) + "\n"
                    + formatter.format(Addess) + "\n"
                    + formatter.format(Phone) + "\n"
                    + formatter.format("1.修改信息") + formatter.format("2.隐私设置") + "\n"
                    + formatter.format("3.修改密码*") + formatter.format("4.返回") + "\n");
            System.out.print('>');

            try {
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                    return;

                switch (instruction) {
                    case 1:
                        //信息修改
                        userService.modifyInfomation();
                        break;
                    case 2:
                        modifyPrivacy();
                        break;
                    case 3:
                        //密码修改
                        userService.modifyPassword();
                        break;
                    default:
                        throw new CustomerException("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("输入无效！");
                informationCheck();
            }
        }
    }

    private void modifyPrivacy()
    {
        int instruction = -1;
        int modify = -1;
        final int EXIT = 0;
        while (true)
        {
            System.out.print("要修改哪一项的可见性?(输入0返回上页)\n");
            System.out.printf("<1>生日: %s\n" +
                              "<2>地址: %s\n" +
                              "<3>电话号码: %s\n>",
                              user.isBorn_access(),
                              user.isAddress_Access(),
                              user.isPhone_Access());
            try
            {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                {
                    System.out.println("正在同步...");
                    userDao.updateUser(user);
                    System.out.println("同步成功!");
                    return;
                }

                System.out.printf("设置可见性为:\n<1> 可见\n<2> 隐藏\n>");
                modify = Integer.parseInt(in.nextLine());
                switch (instruction)
                {
                    case 1:
                        if(modify == 1)
                            user.setBorn_access(true);
                        else
                            user.setBorn_access(false);
                        break;
                    case 2:
                        if(modify == 1)
                            user.setAddress_Access(true);
                        else
                            user.setAddress_Access(false);
                        break;
                    case 3:
                        if(modify == 1)
                            user.setPhone_Access(true);
                        else
                            user.setPhone_Access(false);
                        break;
                    default:
                        throw new CustomerException("Invalid input");
                }

            }
            catch (Exception e)
            {
                if(e instanceof SQLException)
                {
                    System.out.println("数据库连接异常！修改失败");
                    //todo 恢复数据
                }
                else
                    System.out.println("无效输入！");
            }
        }

    }



    public void display()
    {
//        checkNewActivities();
//        checkNews();

        System.out.printf(
                        "Welcome User: %d\n"
                        +"-----------------\n"
                        +"1.我的信息\n"
                        +"2.我的社团\n"
                        +"3.社团浏览\n"
                        +"4.活动墙\n"
                        +"5.消息盒子\n"
                        +"6.退出登陆\n"
                , user.getUser_ID());
        if(News!=0)
            System.out.printf("---你有%d条未读消息---\n",News);

        if(hasNewActivities)
            System.out.println("---活动墙有新活动---");

    }
}
