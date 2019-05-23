package view;
import bean.*;
import dao.UserDao;
import util.CustomerException;
import util.FormatUtil;
import util.StringAlign;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserView extends View{
    private User user;
    private UserDao userDao;
    private int News;
    private boolean hasNewActivities;
    static final String[] PRIVACY = {"(隐藏)","(可见)"};


    public UserView(User user)
    {
        this.user = user;
        this.userDao = new UserDao();
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
                        myClub();
                        break;
                    case 3:
                        //todo 社团浏览
                        clubBrowse();
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
                        "6.返回\n>";
       int instruction = -1;
       final int EXIT = 6;
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

    private void clubBrowse()
    {
        //todo clubBrowse
        String prompt = "----------\n" +
                "当前没有社团\n" +
                "----------\n";
        String content = "1.选择查看详细信息\n" +
                "2.上一页\n" +
                "3.下一页\n" +
                "4.搜索\n" +
                "5.过滤器\n" +
                "6.返回\n>";
        int instruction = -1;
        final int EXIT = 6;
        while (true)
        {
            //todo 获取所有社团
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
                        //todo 选择查看社团的详细信息
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

    private void myClub()
    {
        int instruction = -1;//第一级instruction
        int instruction_2 = -1;//第二级instruction
        final int EXIT = 4;
        while(true)
        {
            //todo 序号+展示所有加入的社团
            //例如：<1>篮球社
            List<Club> clubs = user.getClub();
            int size = clubs.size();
            if(size == 0)
                System.out.printf("-------------\n" +
                                   "没有加入任何社团\n" +
                                   "-------------\n");
            for(int i = 1;i <= size;i ++)
            {
                System.out.printf("<%d>%s\n",i,clubs.get(i-1).getClub_Name());
            }
            //设置中可以修改社团的排序方式(按时间排序还是按人数排序)
            System.out.printf("1.查看社团信息*\n" +
                              "2.创建社团*\n" +
                              "3.资产借用*\n"+
                              "4.返回\n"+">");
            try
            {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;
                switch (instruction)
                {
                    case 1:
                        if(size == 0)
                        {
                            System.out.println("没有可以查看的社团");
                            break;
                        }
                        System.out.printf("选择要查看的社团序号\n>");
                        instruction_2 = Integer.parseInt(in.nextLine());
                        if(instruction_2 >size||instruction_2<1)
                            throw new CustomerException("输入超出范围");
                        //正确输入
                        //todo 展示社团信息
                        clubInfo(clubs.get(instruction_2));
                        break;
                    case 2:
                        //todo 创建社团
                        createClub();
                        break;
                    case 3:
                        //todo 资产借用
                        loanItem();
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

    private void clubInfo(Club club)
    {

    }

    private void createClub()
    {
        final String[] promt = {"输入社团名称(输入0终止)\n>","输入社团类型(输入0终止)\n>","输入社团简介(输入0终止)\n>"};
        String[] name_type_intro = new String[3];
        String instruction;
//        System.out.println("正在创建社团申请...");
//        System.out.print("输入社团名称\n>");
//        //todo 合法性检查
//        String club_name = in.nextLine();
//        System.out.print("输入社团类型\n>");
//        String club_type = in.nextLine();
//        System.out.print("输入社团简介\n>");
//        String club_intro = in.nextLine();
        for(int i = 0;i < promt.length;i ++)
        {
            System.out.print(promt[i]);
            instruction = in.nextLine();
            if(instruction.equals("0")) {
                System.out.println("申请终止!");
                return;
            }
        }
        //todo 向管理员发送创建社团申请
        System.out.println("申请已发送！审核通过后社团将被创建");
    }

    public void loanItem()
    {

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
                        modifyInfomation();
                        break;
                    case 2:
                        modifyPrivacy();
                        break;
                    case 3:
                        //todo 密码修改
                        modifyPassword();
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



    private void modifyPassword()
    {

    }

    private void modifyInfomation()
    {

        int instruction = -1;
        final int EXIT = 0;
        while(true) {
            System.out.printf("要修改哪一项信息?(输入0返回上页)\n>");
            try {
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                {
                    System.out.println("正在同步...");
                    userDao.updateUser(user);
                    System.out.println("同步成功!");
                    return;
                }

                switch (instruction) {
                    case 1:
                        System.out.printf("输入新的姓名\n>");
                        String newName = in.nextLine();
                        nameModify(newName);
                        break;
                    case 2:
                        System.out.printf("输入新的性别\n>");
                        String newSex = in.nextLine();
                        sexModify(newSex);
                        break;
                    case 3:
                        System.out.printf("输入新的生日格式:年-月-日\n例:1990-05-08\n>");
                        String newDate = in.nextLine();
                        bornModify(newDate);
                        break;
                    case 4:
                        System.out.printf("输入新的专业\n>");
                        String newMajor = in.nextLine();
                        majorModify(newMajor);
                        break;
                    case 5:
                        System.out.printf("输入新的地址\n>");
                        String address = in.nextLine();
                        addressModify(address);
                        break;
                    case 6:
                        System.out.printf("输入新的电话号码\n>");
                        String newPhone = in.nextLine();
                        phoneNumberModify(newPhone);
                        break;
                    default:
                        throw new CustomerException("Invalid input");
                }
            } catch (Exception e) {
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

    private void bornModify(String newBorn)
    {
        //todo 检查日期格式
        Date date = fu.stringToDate(newBorn);
        user.setBorn(date);
        System.out.println("生日修改成功!");
    }

    private void majorModify(String newMajor)
    {
        //todo 检查输入合法
        user.setMajor(newMajor);
        System.out.println("专业修改成功!");
    }

    private void addressModify(String newAddress)
    {
        user.setAddress(newAddress);
        System.out.println("地址修改成功!");
    }

    private void phoneNumberModify(String newPhone)
    {
        user.setPhone_Number(newPhone);
        System.out.println("电话号码修改成功!");
    }





    private void nameModify(String newName)
    {
        if(newName.length()<=20)
            user.setName(newName);
        else
            System.out.println("更改失败！输入字符串长度大于20！");
    }

    private void sexModify(String newSex)
    {
        if(newSex.equals(User.GENDER[0])||newSex.equals(User.GENDER[1]))
            user.setSex(newSex);
        else
            System.out.println("无效的性别设置");
    }

    public void display()
    {
        checkNewActivities();
        checkNews();

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

    public void checkNews()
    {
        //todo 检查数据库中有没有新的发给本对象的申请或者公告
        News = 3;//查询结果，这里仅做测试
    }

    public void checkNewActivities()
    {
        //todo 检查数据库中是否有新活动
        hasNewActivities = true;//同checkNews
    }
}
