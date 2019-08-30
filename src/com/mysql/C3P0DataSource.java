package com.mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author fangpf
 * @version 1.0
 * @date 2019-08-30 14:52
 */
public class C3P0DataSource {
    private static final String connectionURL = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
    private static final String username = "root";
    private static final String password = "fang2831016";

    private static ComboPooledDataSource ds;

    static {
        ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
            ds.setJdbcUrl(connectionURL);
            ds.setUser(username);
            ds.setPassword(password);

            ds.setInitialPoolSize(5);
            ds.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
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

}
