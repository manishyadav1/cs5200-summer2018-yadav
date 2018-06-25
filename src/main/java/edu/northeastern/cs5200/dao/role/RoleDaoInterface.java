package edu.northeastern.cs5200.dao.role;

import edu.northeastern.cs5200.models.role.Role;

public interface RoleDaoInterface {

    int insertRole(String roleName);

    Role findRoleByName(String roleName);

    int assignWebsiteRole(int developerId, int websiteId, int roleId);

    int assignPageRole(int developerId, int pageId, int roleId);

    int deleteWebsiteRole(int developerId, int websiteId, int roleId);

    int deletePageRole(int developerId, int pageId, int roleId);
}
