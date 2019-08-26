package com.qf.test;

import com.qf.util.DBUtildbcp;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ program: TestJDBC
 * @ author:  TaoXueFeng
 * @ create: 2019-08-23 11:22
 * @ desc:测试批处理
 **/

public class TestBatch {
    @Test
    public void testBatch() {
        Connection conn = null;
        Statement stat = null;

        int num = 0;

        try {
            conn = DBUtildbcp.getConnection();
            stat = conn.createStatement();
            while (num < 1000) {
                String sql = "insert into testbatch values(null, 'zs" + num + "','f')";
                stat.addBatch(sql);
                if (num % 50 == 0) {
                    stat.executeBatch();
                }
                num++;
            }
        }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

