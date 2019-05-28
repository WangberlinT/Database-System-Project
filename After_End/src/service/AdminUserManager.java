package service;

import bean.User;
import dao.UserDao;
import util.CustomerException;
import util.StringAlign;
import view.View;

public class AdminUserManager extends View {

    //formater extends from View
    //Scanner in extends from View
    UserService userService;

    public AdminUserManager()
    {
        userService = new UserService();
    }
    //显示菜单栏
    private void showMenu()
    {
        formatter = new StringAlign(10,StringAlign.JUST_LEFT);
        System.out.println(formatter.format("---用户管理---"));
        System.out.print("1.查看用户信息\n" +
                         "2.修改用户信息\n" +
                         "3.返回\n" +
                         ">");

    }
    //运行
    public void run()
    {
        int instruction = -1;
        final int EXIT = 3;
        while(true)
        {
            showMenu();
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //查看用户信息
                        userinfoBrowser();
                        break;
                    case 2:
                        //修改用户信息
                        userInfoModify();
                        break;
                    default:
                        throw new CustomerException("输入超限");
                }
            }
            catch (Exception e)
            {

            }
        }
    }
    //修改用户信息
    private void userInfoModify()
    {
        int instruction = -1;
        final int EXIT = 3;
        System.out.print("输入要修改用户的id\n>");
        try {
            int id = Integer.parseInt(in.nextLine());
            UserDao userDao = new UserDao();
            User user = userDao.queryUserByID(id);
            if(user == null)
            {
                System.out.println("用户不存在");
                return;
            }
            userService.setUser(user);
            System.out.print("1.修改个人信息\n" +
                             "2.修改密码\n" +
                             "3.返回\n>");
            instruction = Integer.parseInt(in.nextLine());
            if(instruction == EXIT)
                return;
            if(instruction == 1) {

                userService.modifyInformation();
            }
            else if (instruction == 2)
            {
                userService.searchUserAdmin(id);
                userService.modifyPassword();
            }
        }
        catch (Exception e)
        {
            System.out.println("输入异常");
        }
    }

    //查看用户信息界面
    private void userinfoBrowser()
    {
        String content = "---查看用户信息---\n"+
                         "1.查看所有用户\n"+
                         "2.查看指定id用户\n"+
                         "3.查看指定姓名用户\n"+
                         "4.返回\n"+
                         ">";
        int instruction = -1;
        final int EXIT = 4;
        while(true)
        {
            System.out.print(content);
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        // 查看用户信息
                        userService.searchUserAdmin();
                        break;
                    case 2:
                        //查找指定id用户
                        System.out.print("输入用户ID\n" +
                                         ">");
                        int ID = Integer.parseInt(in.nextLine());
                        userService.searchUserAdmin(ID);
                        break;
                    case 3:
                        //查找指定姓名用户
                        System.out.print("输入用户姓名\n" +
                                ">");
                        String name = in.nextLine();
                        userService.searchUserAdmin(name);
                        break;
                    default:
                        throw new CustomerException("输入超限");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("输入异常");
            }
        }
    }
}
