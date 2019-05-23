package service;

import util.FormatUtil;
import util.StringAlign;

import java.util.List;
import java.util.Scanner;

abstract class BaseService {
    static FormatUtil fu = new FormatUtil();
    StringAlign formatter = new StringAlign(80, StringAlign.JUST_LEFT);


    int PrintPage(int page, List<?> list, Scanner in) {
        int No = (page - 1) * 10 + 1; //这个10是pageSize
        for (Object u : list) {
            System.out.println(formatter.format(No + "  " + u.toString()));
            No++;
        }
        System.out.println("\n请输入指令：1. 上一页   2.  下一页   其他返回");
        int input = in.nextInt();
        switch (input) {
            case 1:
                page--;
                break;
            case 2:
                page++;
                break;
            default:
                page = 0;
        }
        return page;
    }
}
