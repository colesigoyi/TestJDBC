package com.qf;

import com.qf.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @program: TestJDBC
 * @author: TaoXueFeng
 * @create: 2019-08-22 12:37
 * @desc:
 **/

public class Server {
    public static User checkLogin(String username,String pwd){
        if(username==null||username.length()==0){
            return null;
        }
        if(pwd==null||pwd.length()==0){
            return null;
        }
        //将数据传入持久层
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        User user = null;
        try{
            conn = DBUtil.getConnection();
            stat = conn.createStatement();
            String sql = "select * from bank_account where user_name='"+username+"' and user_pwd= "+pwd;
            rs = stat.executeQuery(sql);
            if(rs.next()){
                int id = rs.getInt(1);
                String accountId = rs.getString(2);
                double accountBalance = rs.getDouble(3);
                String userName = rs.getString(4);
                String userPwd = rs.getString(5);
                String userIdcard = rs.getString(6);
                Date operTime = rs.getDate(7);
                String gender = rs.getString(8);
                user = new User(id, accountId, accountBalance, userName,
                        userPwd, userIdcard, operTime, gender);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn,stat,rs);
        }
        return user;
    }
}
