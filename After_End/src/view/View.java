package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import bean.User;
import util.*;

public abstract class View {
    protected Scanner in = new Scanner(System.in);
    protected StringAlign formatter;
    protected FormatUtil fu = new FormatUtil();

    public void displayMenu() {
    }

    //display User
    void PrintUser(User user) {
        String name = "<1>姓名: " + user.getName();
        String gender = "<2>性别: " + user.getSex();
        String born = "<3>生日: " + fu.formatDate(user.getBorn());
        String Major = "<4>专业: " + user.getMajor();
        String Address = "<5>地址: " + user.getAddress();
        String Phone = "<6>电话号码: " + user.getPhone_Number();

        formatter = new StringAlign(20, StringAlign.JUST_LEFT);
        System.out.print(formatter.format(name) + "\n"
                + formatter.format(gender) + "\n"
                + formatter.format(born) + "\n"
                + formatter.format(Major) + "\n"
                + formatter.format(Address) + "\n"
                + formatter.format(Phone) + "\n");
    }

    //输入整数
    int InputInt(Scanner in) {
        int num;
        while (true) {
            try {
                num = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("请输入一个整数：");

            }
            finally {
                in.nextLine();
            }
        }
        return num;
    }
}
