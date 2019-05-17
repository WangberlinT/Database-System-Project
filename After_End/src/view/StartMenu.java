package view;

import dao.BaseDao;
import java.sql.*;
import java.util.*;

public class StartMenu {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("run");
        BaseDao temp = new BaseDao();
        try{
            temp.getCon();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            temp.closeAll();
        }


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
