package com.qf.dao;

import com.qf.entity.Student;

import java.util.List;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 16:24
 * @ desc:
 **/

public interface StudentDao {

    void addStudent(Student s);


    void deleteById(Integer id);


    void modifyStudent(Student s);

    Student findID(int id);


    boolean checkLogin(String name, String password);

}
