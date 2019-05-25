package service;

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
                        //todo 查看用户信息
                        userService.searchUserAdmin(in);
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
}
