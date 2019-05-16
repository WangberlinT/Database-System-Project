package view;
import bean.*;

import java.util.Scanner;


public class AdminView {
    Admin admin;

    public AdminView()
    {

    }

    public boolean accountCheck(String ID,String Password)
    {
        //to do check Table admin,compare ID and Password
        return true;
    }

    public String displayMenu()
    {

        Scanner in = new Scanner(System.in);
        System.out.println("1.Change Password\n"
                            + "2.Manage Club\n"
                            + "3.Manage User\n"
                            + "4.Manage Activity\n"
                            + "5.log out");
        String instruction = in.next();
        return instruction;
    }
// todo change password
//



}
