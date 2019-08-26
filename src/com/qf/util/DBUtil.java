package com.qf.util;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ program: TestJDBC
 * @ author: TaoXueFeng
 * @ create: 2019-08-21 16:47
 * @ desc: 封装了一些对数据库的连接和关闭操作
 **/


public class DBUtil {
    private static String driver ;
    private static String url;
    private static String username;
    private static String password;
    static {
        try {
            //读取配置文件
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //获取Properties对象
            Properties p = new Properties();
            //把流里的内容加载到p
            p.load(is);
            //获取values
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");
            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 连接数据库的方法
     * @return Connection对象
     */
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(
                    url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *关闭连接操作
     * 关闭ResultSet对象
     * 关闭Statement对象
     * 关闭Connection对象
     */
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
    public static void close(Connection con, Statement sta, Statement res) {
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
    @Test
    public void testUtile() {
        Connection connection = getConnection();
        System.out.println(connection);
        closeConnection(connection, null, null);
    }
}
