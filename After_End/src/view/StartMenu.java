package view;

import java.util.*;

public class StartMenu {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {


    }

    public void loginMenu()
    {
        System.out.println("UserID: ");
        String ID = in.next();
        System.out.println("Password: ");
        String password = in.next();

    }

    public void display()
    {
        System.out.println("1.login\n"
                +"2.login(admin)\n"
                +"3.sign up"
                +"4.exit");
    }

}
