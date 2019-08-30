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
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
//        String user = "root";
//        String password = "fang2831016";
        //建立连接
        Connection connection = JDBCUtils.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select  * from users");

        while (rs.next()){
            System.out.println(rs.getInt("userid") + "," + rs.getString("username") + "," +
                    rs.getString("password") + "," + rs.getInt("balance"));
        }

        rs.close();

    }

    public boolean selectByUsernamePassword(String username, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicod=true&characterEncoding=UTF8";
            connection = JDBCUtils.getConnection();
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
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8";
//        String user = "root";
//        String password = "fang2831016";

//        Connection connection = null;
        try {
            Connection connection = JDBCUtils.getConnection();
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

    public void insertTableUsers(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into users(username, password) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int rset = preparedStatement.executeUpdate();
        System.out.println(rset);
    }

    public void alterTableUsers(String colName, int defaultValue) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "alter table users add column ? int null default ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, colName);
        preparedStatement.setInt(2, defaultValue);
        int result = preparedStatement.executeUpdate();
        System.out.println(result);
    }

    public void transfer(String username1, String username2, int cash) throws SQLException, ClassNotFoundException {
//        Connection connection = DPCPDataSource.getConnection();
        Connection connection = C3P0DataSource.getConnection();
        //事务
        connection.setAutoCommit(false);
        String sql = "update users set balance = balance - ? where username = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        preparedStatement1.setInt(1, cash);
        preparedStatement1.setString(2, username1);
        preparedStatement1.executeUpdate();

//        String fang = null;
//        char ss = fang.charAt(2);

        sql = "update users set balance = balance + ? where username = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.setInt(1, cash);
        preparedStatement2.setString(2, username2);
        preparedStatement2.executeUpdate();
        connection.commit();

//        DPCPDataSource.close(preparedStatement1, preparedStatement2, connection);
        C3P0DataSource.close(preparedStatement1, preparedStatement2, connection);
    }

}
