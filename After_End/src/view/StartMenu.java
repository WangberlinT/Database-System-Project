package view;

import java.util.*;

public class StartMenu extends View{
     private int inputID;
     private String inputPassword;
     private boolean isAdmin=false;
     private View view;

    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();

    }

    public void loginMenu()
    {
        System.out.println("UserID: ");
        inputID = Integer.parseInt(in.nextLine());
        System.out.println("Password: ");
        inputPassword = in.nextLine();

        if(!checkPassword())//密码不正确
        {
            System.out.println("密码错误！");
            return;
        }

        //登陆成功
        if(isAdmin)
            view = new AdminView(inputID,inputPassword);
        else
            view = new UserView(inputID,inputPassword);

        view.displayMenu();

    }

    public void display()
    {
        System.out.println("欢迎使用社团管理系统\n"
            + "-----------------\n"
            + "1.登陆\n"
            + "2.登陆(管理员)\n"
            + "3.注册\n"
            + "4.退出\n");
    }


    public void displayMenu()
    {
        final int EXIT = 4;
        int instruction = -1;
        while (true)
        {
            display();
            System.out.print('>');
            String temp = in.nextLine();
            if(temp.length() == 1)
            {
                if(Character.isDigit(temp.charAt(0)))
                    instruction = Character.getNumericValue(temp.charAt(0));
            }

            if(instruction == EXIT)
                break;

            switch (instruction) {
                case 1:
                    isAdmin = false;
                    loginMenu();
                    break;
                case 2:
                    isAdmin = true;
                    loginMenu();
                    break;
                case 3:
                    //todo 注册
                    break;
                default:
                    System.out.println("-------------\n"
                                      +"invalid input\n"
                                      +"-------------\n");
            }
        }
    }

    public boolean checkPassword()
    {
        boolean result = true;
        if(isAdmin)
        {
            //todo Admin Password check
        }
        else
        {
            //todo User Password check
        }
        return result;
    }


}
