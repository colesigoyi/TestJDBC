package com.qf;

import com.qf.util.DBUtil;
import java.sql.*;
import java.util.Scanner;

/**
 * @ program: TestJDBC
 * @ author: TaoXueFeng
 * @ create: 2019-08-22 11:48
 * @ desc:
 **/

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
        if (oneToOne(fromAccount, toAccount, money, fromAccountPwd)) {
            System.out.println("转账完成");
        } else {
            System.out.println("转账失败");
        }
    }
    /**
     * 封装两个人之间的转账逻辑
     *
     * @ return:    true:表示转账成功
     * false：表示转账失败
     */
    private static boolean oneToOne(String fromAccount, String toAccount, double money, String pwd) {
        Connection conn = null;
        PreparedStatement stF = null;
        Statement stT = null;
        ResultSet rsF = null;
        ResultSet rsT = null;
        if (fromAccount.isEmpty()) {
            System.out.println("转账用户用户名输入不能为空！");
            return false;
        } else if (toAccount.isEmpty()) {
            System.out.println("转入用户用户名输入不能为空！");
            return false;
        } else if (money <= 0) {
            System.out.println("转账金额输入错误！");
            return false;
        } else if (pwd.isEmpty()) {
            System.out.println("转账用户密码输入不能为空！");
            return false;
        }
            try {
                conn = DBUtil.getConnection();
                String sqlFrom = "SELECT user_name, user_pwd,account_balance FROM bank_account WHERE user_name = ? and user_pwd = ?";
                stF = conn.prepareStatement(sqlFrom);
                stF.setString(1,fromAccount);
                stF.setString(2,pwd);
                rsF = stF.executeQuery();
                boolean next = rsF.next();
                if (!next) {
                    System.out.println("转入账户用户或密码错误！");
                    return false;
                }else {
                    if (rsF.getDouble("account_balance") >= money) {
                        String sqlFromM = "update  bank_account set account_balance = account_balance - ?  WHERE user_name = ?";
                        stF = conn.prepareStatement(sqlFromM);
                        stF.setDouble(1,money);
                        stF.setString(2,fromAccount);
                        stF.executeUpdate();
                    } else {
                        System.out.println("账户余额不足！");
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.closeConnection(conn, stF, rsF);
            }
            try {
                conn = DBUtil.getConnection();
                String sqlTo = "SELECT user_name, user_pwd,account_balance FROM bank_account WHERE user_name = '" + toAccount + "'";
                stT = conn.createStatement();
                rsT = stT.executeQuery(sqlTo);
                boolean nextT = rsT.next();
                if (!nextT) {
                    System.out.println("转入账号不存在！");
                    return false;
                }else {
                    String sqlToM = "update  bank_account set account_balance = account_balance + " + money + "  WHERE user_name = '" + toAccount + "'";
                    stT = conn.createStatement();
                    stT.executeUpdate(sqlToM);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.closeConnection(conn, stT, rsT);
            }
            return true;

    }
}
