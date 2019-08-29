package com.mysql;

import java.sql.*;

/**
 * @author fangpf
 * @version 1.0
 * @date 2019-08-29 13:47
 */
public class Jdbc01 {

    public void selectAll() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
        String user = "root";
        String password = "fang2831016";
        //建立连接
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select  * from users");

        while (rs.next()){
            System.out.println(rs.getInt("userid") + "," + rs.getString("username") + "," +
                    rs.getString("password") + "," + rs.getString("age"));
        }

        rs.close();

    }

    public boolean selectByUsernamePassword(String username, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicod=true&characterEncoding=UTF8";
            connection = DriverManager.getConnection(url, "root", "fang2831016");
            String sql = "select * from users where username = ? and password = ?";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean selectByUserByPage(int pageNumber, int pageCount) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
        String user = "root";
        String password = "fang2831016";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
            String sql = "select * from users limit ?,?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, (pageNumber-1)*pageCount);
            pst.setInt(2, pageCount);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                System.out.println(rs.getInt("userid") + "," + rs.getString("username") + "," +
                        rs.getString("password"));
                while (rs.next()){
                    System.out.println(rs.getInt("userid") + "," + rs.getString("username") + "," +
                            rs.getString("password"));
                }
                rs.close();
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
