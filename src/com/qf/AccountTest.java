package com.qf;

import com.qf.util.DBUtildbcp;

import java.sql.*;
import java.util.Scanner;

/**
 * @ program TestJDBC
 * @ create 2019-08-22 11:48
 * @ desc:
 * @author taoxuefeng
 * */

public class AccountTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入转出账号：");
        String fromAccount = sc.nextLine();
        System.out.print("请输入转出账号密码：");
        String fromAccountPwd = sc.nextLine();
        System.out.print("请输入转入账号：");
        String toAccount = sc.nextLine();
        System.out.print("请输入转账金额：");
        double money = sc.nextDouble();
        System.out.println("+--------------------+");
        if (oneToOne(fromAccount, toAccount, money, fromAccountPwd)) {
            System.out.println("|      转账完成       |");
        } else {
            System.out.println("|      转账失败       |");
        }
        System.out.println("+--------------------+");
    }
    /**
     * 封装两个人之间的转账逻辑
     * @ return:    true:表示转账成功
     *              false：表示转账失败
     */
    private static boolean oneToOne(String fromAccount, String toAccount, double money, String pwd) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        if (fromAccount.isEmpty()) {
            System.out.println("转账用户用户名输入不能为空!");
            return false;
        } else if (toAccount.isEmpty()) {
            System.out.println("转入用户用户名输入不能为空！");
            return false;
        } else if(fromAccount.equals(toAccount)) {
            System.out.println("用户名不能重复！");
            return false;
        } else if (money <= 0) {
            System.out.println("转账金额输入错误！");
            return false;
        } else if (pwd.isEmpty()) {
            System.out.println("转账用户密码输入不能为空！");
            return false;
        }
        try {
            //conn = DBUtil.getConnection();
            conn = DBUtildbcp.getConnection();
            //取消事务自动提交功能，改为手动提交
            conn.setAutoCommit(false);
            String sqlFrom = "SELECT user_name, user_pwd,account_balance FROM bank_account WHERE user_name = ? and user_pwd = ?";
            st = conn.prepareStatement(sqlFrom);
            st.setString(1,fromAccount);
            st.setString(2,pwd);
            rs = st.executeQuery();
            //System.out.println(rs.getDouble("account_balance"));
            boolean nextf = rs.next();
            if (!nextf) {
                System.out.println("转入账户用户或密码错误！");
                return false;
            }
            double account_balance = rs.getDouble("account_balance");
            System.out.print("查询余额：");

            if (rs.getDouble("account_balance") < money) {
                System.out.println("账户余额不足!");
                return false;
            }
            System.out.println(rs.getDouble("account_balance"));
            String sqlFromM = "update  bank_account set account_balance = account_balance - ?  WHERE user_name = ?";
            st = conn.prepareStatement(sqlFromM);
            st.setDouble(1,money);
            st.setString(2,fromAccount);
            st.executeUpdate();

            //System.out.println("剩余金额：" + rs.getDouble("account_balance"));
            //System.out.println(account_balance);
            //如果在转账中间出现异常

            String sqlTo = "SELECT user_name, user_pwd,account_balance FROM bank_account WHERE user_name = '" + toAccount + "'";
            st = conn.prepareStatement(sqlTo);
            rs = st.executeQuery();
            boolean nextt = rs.next();
            if (!nextt) {
                System.out.println("|  转入账号不存在！  |");
                return false;
            }
            String sqlToM = "update  bank_account set account_balance = account_balance + " + money + "  WHERE user_name = '" + toAccount + "'";
            st = conn.prepareStatement(sqlToM);
            st.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //当出现异常时，才需要回滚事务
            System.out.println("转账终止");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            //DBUtil.closeConnection(conn, st, rs);
            DBUtildbcp.closeConnection(conn, st, rs);
            System.out.println("连接关闭");
        }
        return false;
    }
}
