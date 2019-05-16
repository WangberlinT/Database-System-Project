package view;
import bean.*;

import java.util.Scanner;


public class AdminView {
    Admin admin;
    Scanner in;

    public AdminView(int ID,String password)
    {
        admin = new Admin(ID,password);
        in = new Scanner(System.in);//这里可以继承自登陆页
    }

    public boolean accountCheck(String ID,String Password)
    {
        //to do check Table admin,compare ID and Password
        return true;
    }

    public void displayMenu()
    {
        System.out.printf(
                         "Welcome Admin: %d\n"
                         +"-----------------\n"
                         +"1.修改密码\n"
                         +"2.管理社团\n"
                         +"3.管理用户\n"
                         +"4.管理活动\n"
                         +"5.退出登陆\n"
                         ,admin.getId());


    }
    //Admin 主菜单
    public void mainMenuAction()
    {
        final int EXIT = 5;
        int instruction = -1;

        while(true)
        {
            displayMenu();
            System.out.print('>');
            String temp = in.nextLine();
            if(temp.length() == 1)
            {
                if(Character.isDigit(temp.charAt(0)))
                    instruction = Character.getNumericValue(temp.charAt(0));
            }

            if(instruction == EXIT)
                break;

            switch (instruction)
            {
                case 1:
                    //todo 修改密码界面
                    break;
                case 2:
                    //todo 社团管理界面
                    break;
                case 3:
                    //todo 管理用户界面
                    break;
                case 4:
                    //todo 管理活动界面
                    break;
                default:
                    System.out.println("-------------"
                            +"invalid input\n"
                            +"-------------\n");
            }
        }

    }
// todo change password
    public void changePasswordMenu()
    {
        //输入原密码如果相同则可以修改
        while(true)
        {
            System.out.printf("---更改用户: %d 的密码---\n"
                    +"确保以下操作为本人操作\n"
                    +"1.继续修改\n"
                    +"2.退回上一栏\n",admin.getId());




        }
    }


    public static void main(String[] args)
    {
        AdminView adminView = new AdminView(111,"8888");
        adminView.mainMenuAction();
    }



}
