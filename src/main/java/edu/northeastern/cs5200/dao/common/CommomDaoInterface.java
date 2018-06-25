package edu.northeastern.cs5200.dao.common;

public interface CommomDaoInterface {

    int delete(String sql, int id);

    int assignDeleteRolePriviledge(String sql, int developerId, int entityId, int id);

    int insertRolePriviledge(String sql, String rolePriviledgeName);
}
