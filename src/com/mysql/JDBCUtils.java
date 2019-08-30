package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author fangpf
 * @version 1.0
 * @date 2019-08-29 16:33
 */
public class JDBCUtils {

    public static final String connectionURL = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
    private static final String username = "root";
    private static final String password = "fang2831016";
    private static ArrayList<Connection> conlist = new ArrayList<>();

    static {
        for(int i=0; i<5; i++){
            Connection con = null;
            try {
                con = creatConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conlist.add(con);
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(conlist.isEmpty()==false){
            Connection con = conlist.get(0);
            conlist.remove(con);
            return con;
        }else {
            return creatConnection();
        }
    }

    public static Connection creatConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
//        String user = "root";
//        String password = "fang2831016";
        //建立连接
        Connection connection = DriverManager.getConnection(connectionURL,username,password);

        return connection;
    }
}
