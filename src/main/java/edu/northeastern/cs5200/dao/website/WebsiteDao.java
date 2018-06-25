package edu.northeastern.cs5200.dao.website;

import edu.northeastern.cs5200.dao.JdbcConnection;
import edu.northeastern.cs5200.dao.common.CommomDaoInterface;
import edu.northeastern.cs5200.dao.common.CommonDao;
import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.models.page.Page;
import edu.northeastern.cs5200.models.website.Website;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class WebsiteDao implements WebsiteDaoInterface{

    private static WebsiteDao instance = null;

    private WebsiteDao(){ }

    public static WebsiteDao getInstance(){
        if (instance == null)
            instance = new WebsiteDao();
        return instance;
    }

    @Override
    public int createWebsiteForDeveloper(int developerId, Website website){
        String sql = "insert into WEBSITE (name, description, created, updated, visits, developer) values (?,?,?,?,?,?)";
        return updateCreateWebsite(sql, website, developerId);
    }

    @Override
    public Collection<Website> findAllWebsites() {
        Connection connection = JdbcConnection.getConnection();
        Collection<Website> websites = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from WEBSITE";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Website website = parseWebsiteResultSet(resultSet);
                websites.add(website);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closeStatement(statement);
            JdbcConnection.closeConnection(connection);
        }
        return websites;
    }

    @Override
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Connection connection = JdbcConnection.getConnection();
        Collection<Website> websites = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from WEBSITE where DEVELOPER = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, developerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Website website = parseWebsiteResultSet(resultSet);
                websites.add(website);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return websites;
    }

    @Override
    public Website findWebsiteById(int websiteId) {
        Connection connection = JdbcConnection.getConnection();
        Website website = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from WEBSITE where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, websiteId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                website = parseWebsiteResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return website;
    }

    @Override
    public Website findWebsiteByName(String websiteName) {
        Connection connection = JdbcConnection.getConnection();
        Website website = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from WEBSITE where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, websiteName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                website = parseWebsiteResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return website;
    }

    @Override
    public int updateWebsite(int websiteId, Website website) {
        String sql = "update WEBSITE set name = ?,description = ?,created = ?, updated = ?,visits = ? where id = ?";
        return updateCreateWebsite(sql, website, websiteId);
    }

    @Override
    public int deleteWebsite(int websiteId) {
        String sql = "delete from WEBSITE where id = ?";
        CommomDaoInterface commonDao = CommonDao.getInstance();
        return commonDao.delete(sql, websiteId);
    }

    private Website parseWebsiteResultSet(ResultSet resultSet) {
        Website website = null;
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Date created = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("created"));
            Date updated = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("updated"));
            int visits = resultSet.getInt("visits");
            PageDaoInterface pageDao = PageDao.getInstance();
            Collection<Page> pages = pageDao.findPagesForWebsite(id);
            website = new Website(id, name, description, created, updated, visits, pages);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return website;
    }

    private int updateCreateWebsite(String sql, Website website, int id){
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, website.getName());
            preparedStatement.setString(2, website.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(website.getCreated().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(website.getUpdated().getTime()));
            preparedStatement.setInt(5, website.getVisits());
            preparedStatement.setInt(6, id);
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
}
