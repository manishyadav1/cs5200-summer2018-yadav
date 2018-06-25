package edu.northeastern.cs5200.dao.priviledge;

import edu.northeastern.cs5200.dao.JdbcConnection;
import edu.northeastern.cs5200.dao.common.CommomDaoInterface;
import edu.northeastern.cs5200.dao.common.CommonDao;
import edu.northeastern.cs5200.models.priviledge.Priviledge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriviledgeDao implements PriviledgeDaoInterface {

    private static PriviledgeDao instance = null;

    private PriviledgeDao(){ }

    public static PriviledgeDao getInstance(){
        if (instance == null)
            instance = new PriviledgeDao();
        return instance;
    }

    @Override
    public int insertPriviledge(String priviledgeName) {
        String sql = "insert into PRIVILEDGE (PRIVILEDGENAME) values (?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.insertRolePriviledge(sql, priviledgeName);
    }

    @Override
    public Priviledge findPriviledgeByName(String priviledgeName) {
        String sql = "select * from  PRIVILEDGE where PRIVILEDGENAME = ?";
        Connection connection = JdbcConnection.getConnection();
        Priviledge priviledge = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, priviledgeName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                priviledge = new Priviledge(id, priviledgeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return priviledge;
    }

    @Override
    public int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        String sql = "insert into WEBSITEPRIVILEDGE (developer, website, priviledge) VALUES (?,?,?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, websiteId, priviledgeId);
    }

    @Override
    public int assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
        String sql = "insert into PAGEPRIVILEDGE (developer, page, priviledge) VALUES (?,?,?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, pageId, priviledgeId);
    }

    @Override
    public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        String sql = "delete from WEBSITEPRIVILEDGE where developer = ? and website = ? and priviledge = ?";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, websiteId, priviledgeId);
    }

    @Override
    public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
        String sql = "insert into PAGEPRIVILEDGE (developer, page, priviledge) VALUES (?,?,?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, pageId, priviledgeId);
    }
}
