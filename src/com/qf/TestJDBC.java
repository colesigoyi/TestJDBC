package com.qf;

import java.sql.*;

/**
 * @program: TestJDBC
 * @author: TaoXueFeng
 * @create: 2019-08-21 15:36
 * @desc:
 **/

public class TestJDBC {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //第一步：加载(注册)驱动
            Class.forName("com.mysql.jdbc.Driver");

            //第二步：建立连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bd1901", "root", "root");
            //第三步：获取执行sql的对象statement
            statement = connection.createStatement();
            String sql = "select * from emp";
            //第四步：调用方法执行sql语句，即发送sql
            resultSet = statement.executeQuery(sql);
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
