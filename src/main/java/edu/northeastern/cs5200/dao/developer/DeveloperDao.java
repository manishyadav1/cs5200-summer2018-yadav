package edu.northeastern.cs5200.dao.developer;

import edu.northeastern.cs5200.dao.JdbcConnection;
import edu.northeastern.cs5200.dao.common.CommomDaoInterface;
import edu.northeastern.cs5200.dao.common.CommonDao;
import edu.northeastern.cs5200.models.person.Developer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;

public class DeveloperDao implements DeveloperDaoInterface{

    private static DeveloperDao instance = null;

    private DeveloperDao(){ }

    public static DeveloperDao getInstance(){
        if (instance == null)
            instance = new DeveloperDao();
        return instance;
    }

    @Override
    public int createDeveloper(Developer developer){
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;
        String sql = "insert into PERSON (firstName, lastName, username, password, email, dob) values (?,?,?,?,?,?)";
        String sql1 = "insert into DEVELOPER (id, developerKey) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setString(3, developer.getUsername());
            preparedStatement.setString(4, developer.getPassword());
            preparedStatement.setString(5, developer.getEmail());
            preparedStatement.setDate(6, new java.sql.Date(developer.getDob().getTime()));
            result = preparedStatement.executeUpdate();

            if(result != 0){
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int developerId = resultSet.getInt(1);
                preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setInt(1, developerId);
                preparedStatement1.setString(2, developer.getDeveloperKey());
                result = preparedStatement1.executeUpdate();
            }
            else{
                throw new SQLException("Records not inserted in Person table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closePreparedStatement(preparedStatement1);
            JdbcConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public Collection<Developer> findAllDevelopers(){
        List<Developer> developers = new ArrayList<>();
        Connection connection = JdbcConnection.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from PERSON p, DEVELOPER d WHERE p.id=d.id";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Developer developer = parseDeveloperResultSet(resultSet);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closeStatement(statement);
            JdbcConnection.closeConnection(connection);
        }
        return developers;
    }

    @Override
    public Developer findDeveloperById(int developerId){
        Developer developer = null;
        Connection connection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from PERSON p, DEVELOPER d where p.id=d.id and p.id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, developerId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                developer = parseDeveloperResultSet(resultSet);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return developer;
    }

    @Override
    public Developer findDeveloperByUsername(String username1){
        Developer developer = null;
        Connection connection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from PERSON p, DEVELOPER d where p.id=d.id and p.username=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username1);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                developer = parseDeveloperResultSet(resultSet);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return developer;
    }

    @Override
    public Developer findDeveloperByCredentials(String username, String password){
        Developer developer = null;
        Connection connection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from PERSON p, DEVELOPER d where p.id=d.id and p.username=? and p.password=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                developer = parseDeveloperResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return developer;
    }

    @Override
    public int updateDeveloper(int developerId, Developer developer){
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        String sql = "update PERSON set firstName = ?,lastName = ?,username = ?, password = ?,email = ?,dob = ? WHERE id = ?";
        String sql1 = "update DEVELOPER set developerKey=? WHERE developerId = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setString(3, developer.getUsername());
            preparedStatement.setString(4, developer.getPassword());
            preparedStatement.setString(5, developer.getEmail());
            preparedStatement.setDate(6,new java.sql.Date(developer.getDob().getTime()));
            preparedStatement.setInt(7, developerId);
            result = preparedStatement.executeUpdate();
            if(result != 0){
                preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, developer.getDeveloperKey());
                preparedStatement1.setInt(2, developerId);
                result = preparedStatement1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closePreparedStatement(preparedStatement1);
            JdbcConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int deleteDeveloper(int developerId){
        String sql = "delete from PERSON where id = ?";
        CommomDaoInterface commonDao = CommonDao.getInstance();
        return commonDao.delete(sql,developerId);
    }

    private Developer parseDeveloperResultSet(ResultSet resultSet){
        Developer developer = null;
        try{
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("dob"));
            String developerKey = resultSet.getString("developerKey");
            developer = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
        }
        catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        return developer;
    }
}
