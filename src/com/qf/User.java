package com.qf;

import java.sql.Date;
import java.util.Objects;

/**
 * @program: TestJDBC
 * @author: TaoXueFeng
 * @create: 2019-08-22 12:38
 * @desc:
 **/

public class User {
    private int id;
    private String accountId;
    private double accountBalance;
    private String userName;
    private String userPwd;
    private String userIdcard;
    private Date operTime;
    private String gender;

    public User() {
    }

    public User(int id, String accountId, double accountBalance,
                String userName, String userPwd, String userIdcard, Date operTime, String gender) {
        this.id = id;
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userIdcard = userIdcard;
        this.operTime = operTime;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        User user = (User) o;
        return id == user.id &&
                Double.compare(user.accountBalance, accountBalance) == 0 &&
                Objects.equals(accountId, user.accountId) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPwd, user.userPwd) &&
                Objects.equals(userIdcard, user.userIdcard) &&
                Objects.equals(operTime, user.operTime) &&
                Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, accountBalance, userName,
                userPwd, userIdcard, operTime, gender);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
