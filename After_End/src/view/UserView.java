package view;

import bean.*;
import service.AnnouncementService;
import service.UserService;
import util.CustomerException;

import java.sql.SQLException;

public class UserView extends View {
    private User user;
    private static UserService userService;
    private static AnnouncementService announcementService;
    private long News;
    private long NewActivities;
    private ClubView clubView;
    private ActivityView activityView;

    UserView(User user) {
        this.user = user;
        userService = new UserService(user);
        announcementService = new AnnouncementService();
        clubView = new ClubView(user.getUser_ID());
        activityView = new ActivityView(user);
        News = 0;
    }

    //输出用户菜单
    private void display() {
        News = userService.checkNews(user.getUser_ID());
        NewActivities = userService.checkNewActivities();
        System.out.printf(
                "Welcome User: %d, %s"
                        + "\n-----------------\n"
                        + "1.我的信息\n"
                        + "2.我的社团\n"
                        + "3.活动墙\n"
                        + "4.消息盒子\n"
                        + "5.退出登陆\n"
                , user.getUser_ID(), user.getName());
        if (News > 0)
            System.out.printf("---你有%d条未读消息---\n", News);
    }

    //菜单
    public void displayMenu() {
        int instruction;
        final int EXIT = 5;

        while (true) {
            display();
            System.out.print('>');
            try {
                instruction = InputInt(in);
                if (instruction == EXIT)
                    break;
                switch (instruction) {
                    case 1:
                        informationCheck(); //信息修改，下级菜单在service里
                        break;
                    case 2:
                        //todo 我的社团
                        clubView.start();
                        break;
                    case 3://活动墙浏览
                        activityView.displayMenu();
                        break;
                    case 4:
                        announcementService.checkAnnoToMe(user.getUser_ID()); //查看公告并标为已读
                        News = 0; //新消息已读
                        break;
                    default:
                        throw new CustomerException("Wrong input");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("无效输入！");
            }
        }
    }

    //检查信息
    private void informationCheck() {
        int instruction;
        final int EXIT = 4;
        while (true) {
            //display
            PrintUser(user);
            System.out.print(formatter.format("1.修改信息") + formatter.format("2.隐私设置") + "\n"
                    + formatter.format("3.修改密码*") + formatter.format("4.返回") + "\n");
            System.out.print('>');
            try {
                instruction = InputInt(in);
                if (instruction == EXIT)
                    return;
                switch (instruction) {
                    case 1://信息修改
                        userService.modifyInformation();
                        break;
                    case 2://隐私修改
                        modifyPrivacy();
                        break;
                    case 3://密码修改
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

    //修改密码
    private void modifyPassword() {
        int instruction;
        String inpassword;
        final int EXIT = 2;
        while (true) {
            System.out.printf("---更改用户: %d 的密码---\n"
                    + "确保以下操作为本人操作\n"
                    + "1.继续修改\n"
                    + "2.退回上一栏\n>", user.getUser_ID());
            try {
                instruction = InputInt(in);
                if (instruction == 1) {
                    System.out.print("输入原密码\n>");
                    inpassword = in.nextLine();
                    if (inpassword.equals(user.getPassword())) {
                        System.out.print("输入新密码\n>");
                        inpassword = in.nextLine();
                        user.setPassword(inpassword);
                        System.out.println("正在同步...");
                        userService.updateInfo();
                        System.out.println("更改成功!");
                    } else
                        System.out.println("原密码错误!");
                } else if (instruction == EXIT)
                    break;
                else
                    throw new CustomerException("输入超出范围");
            } catch (Exception e) {
                //todo 异常处理
            }
        }
    }

    //修改用户隐私可见性
    private void modifyPrivacy() {
        int instruction, modify;
        final int EXIT = 0;
        while (true) {
            System.out.print("要修改哪一项的可见性?(输入0返回上页)\n");
            System.out.printf("<1>生日: %s\n" +
                            "<2>地址: %s\n" +
                            "<3>电话号码: %s\n>",
                    user.isBorn_access(),
                    user.isAddress_Access(),
                    user.isPhone_Access());
            try {
                instruction = InputInt(in);
                if (instruction == EXIT) {
                    System.out.println("正在同步...");
                    userService.updateInfo();
                    System.out.println("同步成功!");
                    return;
                }
                System.out.println("设置可见性为:\n<1> 可见\n<2> 隐藏");
                modify = InputInt(in);
                switch (instruction) {
                    case 1:
                        if (modify == 1)
                            user.setBorn_access(true);
                        else
                            user.setBorn_access(false);
                        break;
                    case 2:
                        if (modify == 1)
                            user.setAddress_Access(true);
                        else
                            user.setAddress_Access(false);
                        break;
                    case 3:
                        if (modify == 1)
                            user.setPhone_Access(true);
                        else
                            user.setPhone_Access(false);
                        break;
                    default:
                        throw new CustomerException("Invalid input");
                }
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    System.out.println("数据库连接异常！修改失败");
                    //todo 恢复数据
                } else
                    System.out.println("无效输入！");
            }
        }
    }
}
