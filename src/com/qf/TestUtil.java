package com.qf;

import com.qf.util.DBUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @program: TestJDBC
 * @author: TaoXueFeng
 * @create: 2019-08-21 17:07
 * @desc:
 **/

public class TestUtil {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();

            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //获取Properties对象
            Properties p = new Properties();
            //把流里的内容加载到p
            p.load(is);
            //获取values

            String sql_check = p.getProperty("sql_check");


            resultSet = statement.executeQuery(sql_check);

            System.out.println("编号\t|\t姓名\t|\t工作\t|\t工资");
            System.out.println("---------------------------------------");

            while(resultSet.next()) {
                // 获取第一个列的值 编号id
                int id = resultSet.getInt(1);
                // 获取第二个列的值 图书名称 bookName
                String name = resultSet.getString(2);
                // 获取第三列的值 图书作者 author
                String job = resultSet.getString(3);
                // 获取第四列的值 图书价格 price
                float salary = resultSet.getFloat(6);

                System.out.println(id + "\t|\t" + name + "\t|\t" + job + "\t|\t" + salary + "\t|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement, resultSet);
        }
    }
    @Test
    public void testInsert() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();

            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //获取Properties对象
            Properties p = new Properties();
            //把流里的内容加载到
            p.load(is);
            //获取values

            String sql_add = p.getProperty("sql_add");
            String sql_delete = p.getProperty("sql_delete");

            int i = statement.executeUpdate(sql_add);
            System.out.println("成功插入" + i + "条数据");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement, null);
        }
    }
    @Test
    public void testDelete() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();

            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //获取Properties对象
            Properties p = new Properties();
            //把流里的内容加载到p
            p.load(is);
            //获取values

            String sql_delete = p.getProperty("sql_delete");
            int i1 = statement.executeUpdate(sql_delete);
            System.out.println("成功删除" + i1 + "条数据");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement, null);
        }
    }
}
