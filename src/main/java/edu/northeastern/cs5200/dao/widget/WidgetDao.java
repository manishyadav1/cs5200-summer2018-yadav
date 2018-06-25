package edu.northeastern.cs5200.dao.widget;

import edu.northeastern.cs5200.dao.JdbcConnection;
import edu.northeastern.cs5200.dao.common.CommomDaoInterface;
import edu.northeastern.cs5200.dao.common.CommonDao;
import edu.northeastern.cs5200.models.widget.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WidgetDao implements WidgetDaoInterface {

    private static WidgetDao instance = null;

    private WidgetDao(){ }

    public static WidgetDao getInstance(){
        if (instance == null)
            instance = new WidgetDao();
        return instance;
    }

    @Override
    public int createHeadingWidgetForPage(int pageId, HeadingWidget headingWidget) {
        String sql = "insert into Widget (name, width, height, cssClass, cssStyle, text, `order`, page) values (?,?,?,?,?,?,?,?)";
        int widgetId = createWidget(sql, headingWidget, pageId);

        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement =null;
        String sql1 = "insert into HEADINGWIDGET (id, size) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, widgetId);
            preparedStatement.setInt(2, headingWidget.getSize());
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
    public int createHtmlWidgetForPage(int pageId, HtmlWidget htmlWidget) {
        String sql = "insert into Widget (name, width, height, cssClass, cssStyle, text, `order`, page) values (?,?,?,?,?,?,?,?)";
        int widgetId = createWidget(sql, htmlWidget, pageId);

        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        String sql1 = "insert into HTMLWIDGET (id, html) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, widgetId);
            preparedStatement.setString(2, htmlWidget.getHtml());
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
    public int createImageWidgetForPage(int pageId, ImageWidget imageWidget) {
        String sql = "insert into WIDGET (name, width, height, cssClass, cssStyle, text, `order`, page) values (?,?,?,?,?,?,?,?)";
        int widgetId = createWidget(sql, imageWidget, pageId);

        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        String sql1 = "insert into IMAGEWIDGET (id, src) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, widgetId);
            preparedStatement.setString(2, imageWidget.getSrc());
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
    public int createYouTubeWidgetForPage(int pageId, YouTubeWidget youTubeWidget) {
        String sql = "insert into Widget (name, width, height, cssClass, cssStyle, text, `order`, page) values (?,?,?,?,?,?,?,?)";
        int widgetId = createWidget(sql, youTubeWidget, pageId);

        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        String sql1 = "insert into YOUTUBEWIDGET (id, url, shareble, expandable) values (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, widgetId);
            preparedStatement.setString(2, youTubeWidget.getUrl());
            preparedStatement.setBoolean(3, youTubeWidget.getShareable());
            preparedStatement.setBoolean(4, youTubeWidget.getExpandable());
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
    public Collection<Widget> findAllWidgets() {
        Connection connection = JdbcConnection.getConnection();
        Collection<Widget> widgets = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from WIDGET";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Widget widget = parseWidgetResultSet(resultSet);
                widgets.add(widget);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closeStatement(statement);
            JdbcConnection.closeConnection(connection);
        }
        return widgets;
    }

    @Override
    public Widget findWidgetById(int widgetId) {
        Connection connection = JdbcConnection.getConnection();
        Widget widget = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from WIDGET where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, widgetId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                widget = parseWidgetResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return widget;
    }

    @Override
    public Collection<Widget> findWidgetsForPage(int pageId) {
        Connection connection = JdbcConnection.getConnection();
        Collection<Widget> widgets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from WIDGET where PAGE = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pageId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Widget widget = parseWidgetResultSet(resultSet);
                widgets.add(widget);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return widgets;
    }

    @Override
    public int updateWidget(int widgetId, Widget widget) {
        String sql = "update WIDGET set name = ?,width = ?,height = ?, cssClass = ?,cssStyle = ?, text = ?, `order` = ? where id = ?";
        Connection connection = JdbcConnection.getConnection();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, widget.getName());
            preparedStatement.setInt(2, widget.getWidth());
            preparedStatement.setInt(3, widget.getHeight());
            preparedStatement.setString(4, widget.getCssClass());
            preparedStatement.setString(5, widget.getCssStyle());
            preparedStatement.setString(6, widget.getText());
            preparedStatement.setInt(7, widget.getOrder());
            preparedStatement.setInt(8, widgetId);
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
    public int deleteWidget(int widgetId) {
        String sql = "delete from WIDGET where id = ?";
        CommomDaoInterface commonDao = CommonDao.getInstance();
        return commonDao.delete(sql, widgetId);
    }

    private Widget parseWidgetResultSet(ResultSet resultSet){
        Widget widget = null;
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int width = resultSet.getInt("width");
            int height = resultSet.getInt("height");
            String cssClass = resultSet.getString("cssClass");
            String cssStyle = resultSet.getString("cssStyle");
            String text = resultSet.getString("text");
            int order = resultSet.getInt("order");
            widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return widget;
    }

    private int createWidget(String sql, Widget widget, int id) {
        Connection connection = JdbcConnection.getConnection();
        int result;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, widget.getName());
            preparedStatement.setInt(2, widget.getWidth());
            preparedStatement.setInt(3, widget.getHeight());
            preparedStatement.setString(4, widget.getCssClass());
            preparedStatement.setString(5, widget.getCssStyle());
            preparedStatement.setString(6, widget.getText());
            preparedStatement.setInt(7, widget.getOrder());
            preparedStatement.setInt(8, id);
            result = preparedStatement.executeUpdate();

            if(result == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                return resultSet.getInt(1);
            }
            else{
                throw new SQLException("Records not inserted in Widget table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return 0;
    }
}
