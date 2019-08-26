package com.qf.dao.impl;

import com.qf.dao.StudentDao;
import com.qf.entity.Student;
import com.qf.util.DBUtildbcp;

import java.sql.*;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 16:26
 * @ desc:
 **/

public class StudentDaoImpl implements StudentDao {

    @Override
    public void addStudent(Student s) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtildbcp.getConnection();
            String sql = "insert into Student values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,s.getId());
            ps.setString(2,s.getName());
            ps.setString(3,s.getGender());
            ps.setDate(4,s.getBirth());
            ps.setString(5,s.getPassword());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtildbcp.getConnection();
            String sql = "delete from Student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
    }

    @Override
    public void modifyStudent(Student s) {
        int id = s.getId();
        deleteById(id);
        addStudent(s);
    }

    @Override
    public Student findID(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student s = new Student();
        try {
            conn = DBUtildbcp.getConnection();
            String sql = "select * from Student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idd = rs.getInt(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                Date birth = rs.getDate(4);
                String password = rs.getString(5);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBUtildbcp.closeConnection(conn,ps,null);
        }
        return s;
    }

    @Override
    public boolean checkLogin(String name, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtildbcp.getConnection();
            String sql = "select name, password FROM Student WHERE name = ? and password = ?";
            st = conn.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,password);
            rs = st.executeQuery();

            boolean nextf = rs.next();
            if (!nextf) {
                System.out.println("用户或密码错误！");
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
