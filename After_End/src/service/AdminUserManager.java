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
        userService = new UserService(in);
    }
    //显示菜单栏
    private void showMenu()
    {
        formatter = new StringAlign(10,StringAlign.JUST_CENTER);
        System.out.println(formatter.format("---用户管理---"));
        System.out.print("1.查看用户信息\n" +
                         "2.修改用户信息\n" +
                         "3.添加新用户\n" +
                         "4.返回\n" +
                         ">");

    }
    //运行
    public void run()
    {
        int instruction = -1;
        final int EXIT = 4;
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
                        //todo 修改用户信息
                        break;
                    case 3:
                        //todo 添加新用户
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
    //修改用户信息界面
//    private void userinfoModify()
//    {
//        int instruction = -1;
//        final int EXIT = 0;
//        String content = "1.密码" +
//                         "2.姓名" +
//                         "3.性别" +
//                         "4.出生日期" +
//                         ""
//        while(true)
//        {
//            System.out.print("---修改用户信息---\n" +
//                             "输入要修改用户的ID(输入0返回)\n" +
//                             ">");
//            try {
//                instruction = Integer.parseInt(in.nextLine());
//
//                if(instruction == EXIT)
//                    break;
//                int ID = Integer.parseInt(in.nextLine());
//                userService.searchUserAdmin(ID);
//
//                UserDao userDao = new UserDao();
//                User temp = userDao.queryUserByID(ID);
//
//                System.out.print("要修改哪一项信息\n");
//            }
//            catch (Exception e)
//            {
//
//            }
//        }
//    }

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
                        userService.searchUserAdmin(in);
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
                        userService.searchUserAdmin(name,in);
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
}
