package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class DruidConnection {

    private static Properties properties = null;
    private static DataSource dataSource = null;
    private volatile static DruidConnection instance = null;
    private Connection connection = null;

    //私有构造函数,防止实例化对象
    private DruidConnection() {
    }

    static {
        try {
            properties = new Properties();
            //加载properties
            InputStream is = DruidConnection.class.getClassLoader().getResourceAsStream("druid.properties");
            //加载输入流
            properties.load(is);
            //获取数据源
            
            dataSource = getDatasource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DruidConnection getInstance() {
        if (instance == null) {
            synchronized (DruidConnection.class) {
                if (instance == null)
                    instance = new DruidConnection();
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    // 返回一个链接
    public Connection getConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 加载数据源
    private static DataSource getDatasource() {
        DataSource source = null;
        try {
            source = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return source;
    }

}
