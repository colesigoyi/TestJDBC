package com.qf.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @ program: TestJDBC
 * @author TaoXueFeng
 * @ create: 2019-08-22 16:35
 * @ desc: dbcp连接池
 **/

public class DBUtildbcp {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    /**
     * 连接池属性
     */
    private static String maxTotal;
    private static String maxIdle;
    private static String minIdle;
    private static String initialSize;
    private static String maxWaitMillis;

    /**
    声明一个dbcp连接池变量
     */
    private static BasicDataSource pool;

    static {
        /*
         * 创建连接池对象
         */
        pool = new BasicDataSource();

        //使用类加载器中提供的方法来获取字节流对象，指定配置文件
        InputStream is = DBUtildbcp.class.getClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();

        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * 通过配置文件获得属性值
         */
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        maxTotal = prop.getProperty("maxTotal");
        maxIdle = prop.getProperty("maxIdle");
        minIdle = prop.getProperty("minIdle");
        initialSize = prop.getProperty("initialSize");
        maxWaitMillis = prop.getProperty("maxWaitMillis");
        /*
         * 给连接池参数赋值
         */
        pool.setDriverClassName(driver);
        pool.setUrl(url);
        pool.setUsername(username);
        pool.setPassword(password);
        //连接池支持的最大连接数
        pool.setMaxTotal(Integer.parseInt(maxTotal));
        //连接池支持的最大空闲数
        pool.setMaxIdle(Integer.parseInt(maxIdle));
        //连接池支持的最小空闲数
        pool.setMinIdle(Integer.parseInt(minIdle));
        //连接池初始化的连接数
        pool.setInitialSize(Integer.parseInt(initialSize));
        //连接池空闲等待时间
        pool.setMaxWaitMillis(Long.parseLong(maxWaitMillis));

        //注册驱动可以不需要，因为连接池中以实现注册驱动
        //try {
        //    Class.forName(driver);
        //} catch (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}
    }
    public static Connection getConnection() {
        //从连接池中获取对象
        try {
            /*
             * 返回连接池对象
             */
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConnection(Connection con, Statement sta, ResultSet res) {
        try {
            if (con != null) {
                con.close();
            }
            if (sta != null) {
                sta.close();
            }
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    测试连接池是否可以连接成功
     */
    @Test
    public void testUtile() {
        Connection connection = getConnection();
        System.out.println(connection);
        closeConnection(connection, null, null);
    }
    public static BasicDataSource getPool() {
        return pool;
    }

}
