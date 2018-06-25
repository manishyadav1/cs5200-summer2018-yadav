package edu.northeastern.cs5200.query.create.role;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.dao.role.RoleDao;
import edu.northeastern.cs5200.dao.role.RoleDaoInterface;
import edu.northeastern.cs5200.dao.website.WebsiteDao;
import edu.northeastern.cs5200.dao.website.WebsiteDaoInterface;

public class CreateWebsiteRole {

    public static void createWebsiteRole(){
        //Insert Roles
        insertRole("owner");
        insertRole("admin");
        insertRole("writer");
        insertRole("editor");
        insertRole("reviewer");

        //Insert Roles for Facebook
        insertWebsiteRole("alice", "Facebook", "owner");
        insertWebsiteRole("bob", "Facebook", "editor");
        insertWebsiteRole("charlie", "Facebook", "admin");

        //Insert Roles for Twitter
        insertWebsiteRole("bob", "Twitter", "owner");
        insertWebsiteRole("charlie", "Twitter", "editor");
        insertWebsiteRole("alice", "Twitter", "admin");

        //Insert Roles for Wikipedia
        insertWebsiteRole("charlie", "Wikipedia", "owner");
        insertWebsiteRole("alice", "Wikipedia", "editor");
        insertWebsiteRole("bob", "Wikipedia", "admin");

        //Insert Roles for CNN
        insertWebsiteRole("alice", "CNN", "owner");
        insertWebsiteRole("bob", "CNN", "editor");
        insertWebsiteRole("charlie", "CNN", "admin");

        //Insert Roles for CNET
        insertWebsiteRole("bob", "CNET", "owner");
        insertWebsiteRole("charlie", "CNET", "editor");
        insertWebsiteRole("alice", "CNET", "admin");

        //Insert Roles for Gizmodo
        insertWebsiteRole("charlie", "Gizmodo", "owner");
        insertWebsiteRole("alice", "Gizmodo", "editor");
        insertWebsiteRole("bob", "Gizmodo", "admin");

    }

    private static void insertRole(String roleName){
        int result;
        RoleDaoInterface roleDao = RoleDao.getInstance();
        result = roleDao.insertRole(roleName);
        if (result==1){
            System.out.println(String.format("Role %s created.", roleName));
        }
        else{
            System.out.println(String.format("Role %s not created.", roleName));
        }
    }

    private static void insertWebsiteRole(String developerName, String websiteName, String roleName){
        int result;
        DeveloperDaoInterface developerDao = DeveloperDao.getInstance();
        WebsiteDaoInterface websiteDao = WebsiteDao.getInstance();
        RoleDaoInterface roleDao =RoleDao.getInstance();

        int developerId = developerDao.findDeveloperByUsername(developerName).getId();
        int websiteId = websiteDao.findWebsiteByName(websiteName).getId();
        int roleId = roleDao.findRoleByName(roleName).getId();
        result = roleDao.assignWebsiteRole(developerId, websiteId, roleId);
        if (result==1){
            System.out.println(String.format("Role %s for Website %s and developer %s created.", roleName, websiteName,
                    developerName));
        }
        else{
            System.out.println(String.format("Role %s for Website %s and developer %s not created.", roleName, websiteName,
                    developerName));
        }
    }
}
