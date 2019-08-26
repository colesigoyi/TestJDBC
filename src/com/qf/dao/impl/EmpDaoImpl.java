package com.qf.dao.impl;

import com.qf.dao.EmpDao;
import com.qf.entity.Emp;
import com.qf.util.DBUtildbcp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 10:02
 * @ desc:实现类
 **/

public class EmpDaoImpl implements EmpDao {

    @Override
    public void addEmp(Emp e) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtildbcp.getConnection();
            String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,e.getEmpno());
            ps.setString(2,e.getEname());
            ps.setString(3,e.getJob());
            ps.setInt(4,e.getMgr());
            ps.setDate(5,e.getHiredate());
            ps.setDouble(6,e.getComm());
            ps.setDouble(7,e.getSalary());
            ps.setInt(8,e.getDeptno());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
    }

    @Override
    public void deleteById(Integer empno) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtildbcp.getConnection();
            String sql = "delete from EMP where EMPNO = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,empno);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
    }

    @Override
    public void modifyEmp(Emp e) {
        int id = e.getEmpno();
        deleteById(id);
        addEmp(e);
    }

    @Override
    public Emp findById(Integer empno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Emp emp = new Emp();
        try {
            conn = DBUtildbcp.getConnection();
            String sql = "select * from EMP where EMPNO = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,empno);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgs = rs.getInt(4);
                Date hiredate = rs.getDate(5);
                double comm = rs.getDouble(6);
                double salary = rs.getDouble(7);
                int deptno = rs.getInt(8);
                emp = new Emp(id,ename,job,mgs,hiredate,comm,salary,deptno);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
        return emp;
    }

    @Override
    public List<Emp> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Emp> emps = new ArrayList<>();
        try {
            conn = DBUtildbcp.getConnection();
            String sql = "select * from emp";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgs = rs.getInt(4);
                Date hiredate = rs.getDate(5);
                double comm = rs.getDouble(6);
                double salary = rs.getDouble(7);
                int deptno = rs.getInt(8);
                Emp e = new Emp(empno,ename,job,mgs,hiredate,comm,salary,deptno);
                emps.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
        return emps;
    }

    @Override
    public List<Emp> findByPage(Integer page, Integer pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Emp> emps = new ArrayList<>();
        try {
            conn = DBUtildbcp.getConnection();
            String sql = "select * from emp order by DEPTNO limit ?,?";
            ps = conn.prepareStatement(sql);
            int num = (page-1) * pageSize;
            ps.setInt(1,num);
            ps.setInt(2,pageSize);
            rs = ps.executeQuery();

            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgs = rs.getInt(4);
                Date hiredate = rs.getDate(5);
                double comm = rs.getDouble(6);
                double salary = rs.getDouble(7);
                int deptno = rs.getInt(8);

                Emp e = new Emp(empno,ename,job,mgs,hiredate,comm,salary,deptno);
                emps.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
        return emps;
    }
}
