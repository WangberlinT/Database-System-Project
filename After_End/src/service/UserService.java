package service;

import bean.User;
import dao.UserDao;
import util.FormatUtil;

import java.util.Date;

public class UserService {
    private static UserDao userDao = new UserDao();
    private static FormatUtil fu = new FormatUtil();
    private User user;

    //换名字
    private void nameModify(String newName) {
        if (newName.length() <= 20)
            user.setName(newName);
        else
            System.out.println("更改失败！输入字符串长度大于20！");
    }

    //换性别
    private void sexModify(String newSex) {
        if (newSex.equals(User.GENDER[0]) || newSex.equals(User.GENDER[1]))
            user.setSex(newSex);
        else
            System.out.println("无效的性别设置");
    }

    //修改生日
    private void bornModify(String newBorn) {
        //todo 检查日期格式
        Date date = fu.stringToDate(newBorn);
        user.setBorn(date);
        System.out.println("生日修改成功!");
    }

    //修改专业
    private void majorModify(String newMajor) {
        //todo 检查输入合法
        user.setMajor(newMajor);
        System.out.println("专业修改成功!");
    }

    //修改地址
    private void addressModify(String newAddress) {
        user.setAddress(newAddress);
        System.out.println("地址修改成功!");
    }

    //修改电话
    private void phoneNumberModify(String newPhone) {
        user.setPhone_Number(newPhone);
        System.out.println("电话号码修改成功!");
    }

    //检查新信息
    public void checkNews() {
        //todo 检查数据库中有没有新的发给本对象的申请或者公告
//        News = 3;//查询结果，这里仅做测试
    }

    //检查新活动
    public void checkNewActivities() {
        //todo 检查数据库中是否有新活动
//        hasNewActivities = true;//同checkNews
    }
}
