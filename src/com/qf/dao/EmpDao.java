package com.qf.dao;

import com.qf.entity.Emp;

import java.util.List;

/**
 * @ program: TestJDBC
 * @ author: TaoXueFeng
 * @ create: 2019-08-23 09:48
 * @ desc:设计针对于实体类Emp和数据库里的emp表设计对数据库操作的接口
 *          提供相应的抽象方法
 **/

public interface EmpDao {

    /**
     * 提供向数据库中拆入数据库的方法，
     * @param e: 面向对象思想可以使用实体类实例
     */
    void addEmp(Emp e);

    /**
     * 提供删除数据库的一条记录方法，通过主键或唯一键（id）进行删除
     * @param empno  数据库表中的主键
     */
    void deleteById(Integer empno);

    /**
     * 修改方法
     * @param e：传入前先设置成要修改的数据，然后传入方法中进行update语句赋值
     */
    void modifyEmp(Emp e);

    /**
     * 通过主键查询一条记录
     * @param empno：主键
     * @return ： 冯装成实体类实例
     */
    Emp findById(Integer empno);

    /**
     * 查询所有记录
     * @return ：封装成类的实例，并存如集合
     */
    List<Emp> findAll();

    /**
     * 分页查询
     * @param page：要查询的页数
     * @param pageSize：每页显示的条数
     * @return ：整页的数据，封装到集合中
     */
    List<Emp>findByPage(Integer page, Integer pageSize);


}
