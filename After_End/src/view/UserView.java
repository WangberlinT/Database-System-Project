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
        int instruction = -1;
        final int EXIT = 4;
        while(true)
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

            switch (instruction)
            {
                case 1:
                    //todo 我的信息
                    break;
                case 2:
                    //todo 我的社团
                    break;
                case 3:
                    //todo 社团浏览
                    break;
                default:
                    System.out.println("-------------"
                            +"invalid input\n"
                            +"-------------\n");
            }
        }

    }

    public void display()
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
