package com.mysql;

import com.mysql.cj.xdevapi.SqlStatement;
import com.sun.codemodel.internal.JStatement;

import java.sql.*;

/**
 * @author fangpf
 * @version 1.0
 * @date 2019-08-29 13:47
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	// write your code here
//        new Jdbc01().selectAll();
//        boolean selectResult = new Jdbc01().selectByUsernamePassword("fang", "123");
//        System.out.println(selectResult);

//        new Jdbc01().selectByUserByPage(1, 6);
//        new Jdbc01().insertTableUsers("fangpengfei", "7654321");
        Jdbc01 jdbc = new Jdbc01();
        jdbc.selectAll();
        jdbc.transfer("fang", "jim", 2000);
//        jdbc.alterTableUsers("balance", 5000);
        jdbc.selectAll();
    }
}
