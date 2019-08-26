package com.qf.test;

import com.qf.entity.Emp;
import com.qf.util.DBUtildbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 11:03
 * @ desc: 测试jdbc第三方工具类
 **/

public class TestDbUtils {
    @Test
    public void testFindOne() {
        QueryRunner qr = new QueryRunner(DBUtildbcp.getPool());
        try {
            Emp emp = qr.query("select * from emp", new BeanHandler<Emp>(Emp.class));
            System.out.println(emp.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFindById() {
        QueryRunner qr = new QueryRunner(DBUtildbcp.getPool());
        try {
            Emp emp = qr.query("select * from emp where empno = ?", new BeanHandler<Emp>(Emp.class),9002);
            System.out.println(emp.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
