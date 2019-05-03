package dbcontrol;

import java.sql.*;

public class DatabaseControler {

    private String dbname;
    private String url;
    private String username;
    private String password;

    Connection connection;
    Statement statement;

//--------------------------------------------------------------------------------

    /**
     * set DBC configration and connect to default database
     */
    DatabaseControler()
    {
        dbname = "mydb";
        url = "jdbc:mysql://localhost:3306/"+dbname+"?useSSL=false&serverTimezone=UTC";
        username = "root";
        password = "root";

        try
        {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * connect specified database
     * @param dbname database name
     * @param url database url
     * @param username login username
     * @param password login password
     */

    DatabaseControler(String dbname,String url,String username,String password)
    {
        this.dbname = dbname;
        this.url = url+dbname+"?useSSL=false&serverTimezone=UTC";
        this.username = username;
        this.password = password;

        try
        {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

//--------------------------------------------------------------------------------
//Query method
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
//Manipulate method
//--------------------------------------------------------------------------------

    public void insertClub(int club_id,String club_name,
                           String club_type,String club_intro,int club_leader)
    {
        String sql_state = "insert into Club "
                         + " (Club_ID,Club_Name,Club_Type,Club_Intro,Club_Leader)"
                         + " Values "
                         + " ("+ club_id + "," + "'" + club_name + "',"
                         + "'" + club_type + "'," + "'" + club_intro + "',"
                         + club_leader + ")";


    }

//--------------------------------------------------------------------------------
//Tools method
//--------------------------------------------------------------------------------


//    public static void main(String[] args)
//    {
//        DatabaseControler dbc = new DatabaseControler();
//
//        try
//        {
//            ResultSet resultSet = dbc.statement.executeQuery("select * from club");
//
//            while(resultSet.next())
//                System.out.println(resultSet.getString("Club_Name")
//                + "," +resultSet.getString("Club_Type"));
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
}
