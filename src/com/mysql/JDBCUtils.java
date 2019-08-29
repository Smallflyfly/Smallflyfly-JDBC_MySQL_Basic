package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author fangpf
 * @version 1.0
 * @date 2019-08-29 16:33
 */
public class JDBCUtils {

    public static final String connectionURL = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";

    private static final String username = "root";
    private static final String password = "fang2831016";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
//        String user = "root";
//        String password = "fang2831016";
        //建立连接
        Connection connection = DriverManager.getConnection(connectionURL,username,password);

        return connection;
    }
}
