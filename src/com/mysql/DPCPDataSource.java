package com.mysql;

import com.sun.tools.doclets.formats.html.PackageUseWriter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author fangpf
 * @version 1.0
 * @date 2019-08-30 11:27
 */
public class DPCPDataSource {
    private static final String connectionURL = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
    private static final String username = "root";
    private static final String password = "fang2831016";

    public static BasicDataSource ds;

    static {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(connectionURL);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setInitialSize(5);
        ds.setMaxTotal(20);
        ds.setMinIdle(3);
    }

    public static void closeResult(ResultSet rst){
        try {
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement1, Statement statement2, Connection connection){
        closeStatement(statement1);
        closeStatement(statement2);
        closeConnection(connection);
    }

    public static void closeStatement(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
