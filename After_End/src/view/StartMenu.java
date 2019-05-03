package view;

import java.util.*;

public class StartMenu {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {


        System.out.println("1.login\n"
                          +"2.register\n"
                          +"3.exit");

        String instruction = in.next();
        switch (instruction)
        {
            case "1":
                loginMenu();
                break;
            case "2":
                //todo register menu
                break;
            case "3":
                System.out.println("Bye");
                break;
            default:
                System.out.println("invalid input");

        }

    }

    public static void loginMenu()
    {
        System.out.println("UserID: ");
        String ID = in.next();
        System.out.println("Password: ");
        String password = in.next();
        if(!checkNum(ID))
        {
            AdminView admin = new AdminView(ID,password);
            admin.displayMenu();
        }
    }

    /**
     * check whether string is number
     * if it is number, account is user
     * if it contains letter, it is admin
     * @param str
     * @return
     */
    private static boolean checkNum(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
