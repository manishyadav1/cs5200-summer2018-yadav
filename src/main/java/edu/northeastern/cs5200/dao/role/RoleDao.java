package edu.northeastern.cs5200.dao.role;

import edu.northeastern.cs5200.dao.JdbcConnection;
import edu.northeastern.cs5200.dao.common.CommomDaoInterface;
import edu.northeastern.cs5200.dao.common.CommonDao;
import edu.northeastern.cs5200.models.role.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao implements RoleDaoInterface {

    private static RoleDao instance = null;

    private RoleDao(){ }

    public static RoleDao getInstance(){
        if (instance == null)
            instance = new RoleDao();
        return instance;
    }

    @Override
    public int insertRole(String roleName) {
        String sql = "insert into ROLE (ROLENAME) values (?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.insertRolePriviledge(sql, roleName);
    }

    @Override
    public Role findRoleByName(String roleName) {
        String sql = "select * from  ROLE where ROLENAME = ?";
        Connection connection = JdbcConnection.getConnection();
        Role role = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roleName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                role = new Role(id, roleName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcConnection.closeResultSet(resultSet);
            JdbcConnection.closePreparedStatement(preparedStatement);
            JdbcConnection.closeConnection(connection);
        }
        return role;
    }

    @Override
    public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
        String sql = "insert into WEBSITEROLE (developer, website, role) VALUES (?,?,?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, websiteId, roleId);
    }

    @Override
    public int assignPageRole(int developerId, int pageId, int roleId) {
        String sql = "insert into PAGEROLE (developer, page, role) VALUES (?,?,?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, pageId, roleId);
    }

    @Override
    public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        String sql = "delete from WEBSITEROLE where developer = ? and website = ? and role = ?";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, websiteId, roleId);
    }

    @Override
    public int deletePageRole(int developerId, int pageId, int roleId) {
        String sql = "insert into PAGEROLE (developer, page, role) VALUES (?,?,?)";
        CommomDaoInterface commomDao = CommonDao.getInstance();
        return commomDao.assignDeleteRolePriviledge(sql, developerId, pageId, roleId);
    }
}
