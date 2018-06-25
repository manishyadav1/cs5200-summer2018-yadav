package edu.northeastern.cs5200.dao;

import java.sql.*;

public class JdbcConnection {

    private Connection connection;
    private static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/hw3_yadav_manish_summer_2018?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(CONNECTIONSTRING, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void  closePreparedStatement(PreparedStatement preparedStatement){
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void  closeStatement(Statement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
