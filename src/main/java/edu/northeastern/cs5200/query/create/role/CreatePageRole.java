package edu.northeastern.cs5200.query.create.role;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.dao.role.RoleDao;
import edu.northeastern.cs5200.dao.role.RoleDaoInterface;

public class CreatePageRole {

    public static void createPageRole(){

        //Insert Roles For Home
        insertPageRole("alice", "Home", "editor");
        insertPageRole("bob", "Home", "reviewer");
        insertPageRole("charlie", "Home", "writer");

        //Insert Roles For About
        insertPageRole("bob", "About", "editor");
        insertPageRole("charlie", "About", "reviewer");
        insertPageRole("alice", "About", "writer");

        //Insert Roles For Contact
        insertPageRole("charlie", "Contact", "editor");
        insertPageRole("alice", "Contact", "reviewer");
        insertPageRole("bob", "Contact", "writer");

        //Insert Roles For Preferences
        insertPageRole("alice", "Preferences", "editor");
        insertPageRole("bob", "Preferences", "reviewer");
        insertPageRole("charlie", "Preferences", "writer");

        //Insert Roles For Profile
        insertPageRole("bob", "Profile", "editor");
        insertPageRole("charlie", "Profile", "reviewer");
        insertPageRole("alice", "Profile", "writer");
    }

    private static void insertPageRole(String developerName, String pageName, String roleName){
        int result;
        DeveloperDaoInterface developerDao = DeveloperDao.getInstance();
        PageDaoInterface pageDao = PageDao.getInstance();
        RoleDaoInterface roleDao =RoleDao.getInstance();

        int developerId = developerDao.findDeveloperByUsername(developerName).getId();
        int websiteId = pageDao.findPageByName(pageName).getId();
        int roleId = roleDao.findRoleByName(roleName).getId();
        result = roleDao.assignWebsiteRole(developerId, websiteId, roleId);
        if (result==1){
            System.out.println(String.format("Role %s for Page %s and developer %s created.", roleName, pageName,
                    developerName));
        }
        else{
            System.out.println(String.format("Role %s for Page %s and developer %s not created.", roleName, pageName,
                    developerName));
        }
    }
}
