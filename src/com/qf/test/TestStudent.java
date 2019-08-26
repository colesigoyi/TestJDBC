package com.qf.test;

import com.qf.dao.StudentDao;
import com.qf.dao.impl.StudentDaoImpl;
import com.qf.entity.Student;
import org.junit.Test;

import java.sql.Date;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 16:41
 * @ desc:
 **/

public class TestStudent {
    @Test
    public void testAddEmp() {
        StudentDao studentDao = new StudentDaoImpl();
        Student s = new Student(1003,"liuliu","f",Date.valueOf("1999-2-2"),"liuliu");
        studentDao.addStudent(s);
    }
    @Test
    public void testDeleteById() {
        StudentDao studentDao = new StudentDaoImpl();
        studentDao.deleteById(1002);
    }

    @Test
    public void testModify() {
        StudentDao studentDao = new StudentDaoImpl();
        Student s = new Student(1001,"lisi","m",Date.valueOf("1999-12-31"),"lisi");
        studentDao.modifyStudent(s);
    }
    @Test
    public void testCheckLogin() {
        StudentDao studentDao = new StudentDaoImpl();
        boolean b = studentDao.checkLogin("lisi", "lisi");
        if (b) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
    @Test
    public void testFindById() {
        StudentDao studentDao = new StudentDaoImpl();
        Student s = studentDao.findID(1001);
        System.out.println(s.toString());
    }
}
