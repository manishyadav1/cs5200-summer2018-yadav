package edu.northeastern.cs5200.dao.common;

import edu.northeastern.cs5200.dao.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonDao implements CommomDaoInterface{

    private static CommonDao instance = null;

    private CommonDao(){ }

    public static CommonDao getInstance(){
        if (instance == null)
            instance = new CommonDao();
        return instance;
    }

    @Override
    public int delete(String sql, int id) {
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int assignDeleteRolePriviledge(String sql, int developerId, int entityId, int rolePriviledgeId){
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, entityId);
            preparedStatement.setInt(3, rolePriviledgeId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int insertRolePriviledge(String sql, String rolePriviledgeName) {
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, rolePriviledgeName);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return result;
    }
}
