package com.qf.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * @ program: TestJDBC
 * @ author TaoXueFeng
 * @ create: 2019-08-23 09:43
 * @ desc: 以orm关系讲数据库中的emp表映射成java中的emp类型
 *          表的字段映射成类的属性
 **/

public class Emp {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private double comm;
    private double salary;
    private int deptno;

    public Emp() {
    }

    public Emp(int empno, String ename, String job, int mgr,
               Date hiredate, double comm, double salary, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.comm = comm;
        this.salary = salary;
        this.deptno = deptno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emp emp = (Emp) o;
        return empno == emp.empno &&
                mgr == emp.mgr &&
                Double.compare(emp.comm, comm) == 0 &&
                Double.compare(emp.salary, salary) == 0 &&
                deptno == emp.deptno &&
                Objects.equals(ename, emp.ename) &&
                Objects.equals(job, emp.job) &&
                Objects.equals(hiredate, emp.hiredate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, ename, job, mgr, hiredate, comm, salary, deptno);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", comm=" + comm +
                ", salary=" + salary +
                ", deptno=" + deptno +
                '}';
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}
