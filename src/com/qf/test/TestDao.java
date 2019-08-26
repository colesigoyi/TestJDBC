package com.qf.test;

import com.qf.dao.EmpDao;
import com.qf.dao.impl.EmpDaoImpl;
import com.qf.entity.Emp;
import org.junit.Test;
import java.sql.Date;
import java.util.List;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 10:09
 * @ desc:
 **/

public class TestDao {
    @Test
    public void testAddEmp() {
        EmpDao dao = new EmpDaoImpl();
        Emp e = new Emp(9007, "qf", "manager",
                7369, Date.valueOf("2019-01-01"), 300.0, 2000, 20);
        dao.addEmp(e);
    }

    @Test
    public void testFindByPage() {
        EmpDao dao = new EmpDaoImpl();
        List<Emp> emps = dao.findByPage(3, 5);
        for (int i = 0; i < emps.size(); i++) {
            Emp e = emps.get(i);
            System.out.println(e.toString());
        }
    }

    @Test
    public void testDeleteById() {
        EmpDao dao = new EmpDaoImpl();
        dao.deleteById(9007);
    }

    @Test
    public void testModifyEmp() {
        EmpDao empDao = new EmpDaoImpl();
    }

    @Test
    public void testFindById() {
        EmpDao dao = new EmpDaoImpl();
        Emp e = dao.findById(9002);
        System.out.println(e.toString());
    }

    @Test
    public void testFindAll() {
        EmpDao dao = new EmpDaoImpl();
        List<Emp> emps = dao.findAll();
        for (int i = 0; i < emps.size(); i++) {
            Emp e = emps.get(i);
            System.out.println(e.toString());
        }
    }
    @Test
    public void testModify() {
        EmpDao dao = new EmpDaoImpl();
        Emp e = new Emp(9002,"superman","manager",7369,Date.valueOf("1992-8-7"),1000,5000,20);
        dao.modifyEmp(e);
    }
}