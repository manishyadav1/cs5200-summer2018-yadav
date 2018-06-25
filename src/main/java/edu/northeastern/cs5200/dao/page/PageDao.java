package edu.northeastern.cs5200.dao.page;

import edu.northeastern.cs5200.dao.JdbcConnection;
import edu.northeastern.cs5200.dao.common.CommomDaoInterface;
import edu.northeastern.cs5200.dao.common.CommonDao;
import edu.northeastern.cs5200.dao.widget.WidgetDao;
import edu.northeastern.cs5200.dao.widget.WidgetDaoInterface;
import edu.northeastern.cs5200.models.page.Page;
import edu.northeastern.cs5200.models.widget.Widget;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PageDao implements PageDaoInterface{

    private static PageDao instance = null;

    private PageDao(){ }

    public static PageDao getInstance(){
        if (instance == null)
            instance = new PageDao();
        return instance;
    }


    @Override
    public int createPageForWebsite(int websiteId, Page page) {
        String sql = "insert into PAGE (title, description, created, updated, views, website) values (?,?,?,?,?,?)";
        return updateCreatePage(sql, page, websiteId);
    }

    @Override
    public Collection<Page> findAllPages() {
        Connection connection = JdbcConnection.getConnection();
        Collection<Page> pages = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from PAGE";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Page page = parsePageResultSet(resultSet);
                pages.add(page);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closeStatement(statement);
            JdbcConnection.closeConnection(connection);
        }
        return pages;
    }

    @Override
    public Page findPageById(int pageId) {
        Connection connection = JdbcConnection.getConnection();
        Page page = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from PAGE where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pageId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                page = parsePageResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return page;
    }

    @Override
    public Page findPageByName(String pageName){
        Connection connection = JdbcConnection.getConnection();
        Page page = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from PAGE where title = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pageName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                page = parsePageResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return page;
    }

    @Override
    public Collection<Page> findPagesForWebsite(int websiteId){
        Connection connection = JdbcConnection.getConnection();
        Collection<Page> pages = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from PAGE where WEBSITE = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, websiteId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Page page = parsePageResultSet(resultSet);
                pages.add(page);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return pages;
    }

    @Override
    public int updatePage(int pageId, Page page) {
        String sql = "update PAGE set title = ?,description = ?,created = ?, updated = ?,views = ? where id = ?";
        return updateCreatePage(sql, page, pageId);
    }

    @Override
    public int deletePage(int pageId) {
        String sql = "delete from PAGE where id = ?";
        CommomDaoInterface commonDao = CommonDao.getInstance();
        return commonDao.delete(sql, pageId);
    }

    private Page parsePageResultSet(ResultSet resultSet){
        Page page = null;
        try {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            Date created = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("created"));
            Date updated = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("updated"));
            int views = resultSet.getInt("views");
            WidgetDaoInterface widgetDao = WidgetDao.getInstance();
            Collection<Widget> widgets = widgetDao.findWidgetsForPage(id);
            page = new Page(id, title, description, created, updated, views, widgets);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return page;
    }

    private int updateCreatePage(String sql, Page page, int id) {
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, page.getTitle());
            preparedStatement.setString(2, page.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(page.getCreated().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(page.getUpdated().getTime()));
            preparedStatement.setInt(5, page.getViews());
            preparedStatement.setInt(6, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return result;
    }
}
