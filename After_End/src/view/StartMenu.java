package view;

import java.util.*;

public class StartMenu {
     protected Scanner in = new Scanner(System.in);
     private int inputID;
     private String inputPassword;
     private boolean isAdmin=false;
     private View view;
    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        startMenu.loginMenu();

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
        while (true)
        {
            System.out.println("欢迎使用社团管理系统\n"
                    + "-----------------"
                    + "1.登陆\n"
                    + "2.登陆(管理员)\n"
                    + "3.注册"
                    + "4.退出");
            String instruction = in.nextLine();

            if (instruction.equals("4"))
                return;

            switch (instruction) {
                case "1":
                    loginMenu();
                    isAdmin = false;
                    break;
                case "2":
                    loginMenu();
                    isAdmin = true;
                    break;
                case "3":
                    //todo 注册
                    break;
                default:
                    System.out.println("invalid");
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
