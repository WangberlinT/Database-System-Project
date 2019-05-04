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

    public ResultSet query(String sql)
    {
        try {
            return this.statement.executeQuery(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

//--------------------------------------------------------------------------------
//Manipulate method
//--------------------------------------------------------------------------------



//--------------------------------------------------------------------------------
//Tools method
//--------------------------------------------------------------------------------


    public static void main(String[] args)
    {
        DatabaseControler dbc = new DatabaseControler();

        try
        {
            ResultSet resultSet = dbc.statement.executeQuery("select * from club");

            while(resultSet.next())
                System.out.println(resultSet.getString("Club_Name")
                + "," +resultSet.getString("Club_Type"));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
