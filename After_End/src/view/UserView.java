package view;
import bean.*;

public class UserView extends View{
    private User user;

    public UserView(int ID,String password)
    {
        user = new User(ID,password);
    }
    public void displayMenu()
    {
        System.out.printf(
                "Welcome User: %d\n"
                        +"-----------------\n"
                        +"1.我的信息\n"
                        +"2.我的社团\n"
                        +"3.社团浏览\n"
                        +"4.退出登陆\n"
                ,user.getUserID());

    }
}
