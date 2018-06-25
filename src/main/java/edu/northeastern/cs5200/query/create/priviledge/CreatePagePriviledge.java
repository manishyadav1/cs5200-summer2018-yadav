package edu.northeastern.cs5200.query.create.priviledge;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.dao.priviledge.PriviledgeDao;
import edu.northeastern.cs5200.dao.priviledge.PriviledgeDaoInterface;

public class CreatePagePriviledge {

    public static void createPagePriviledge(){
        // Create Priviledge For Home
        insertPagePriviledge("alice", "Home", "read");
        insertPagePriviledge("alice", "Home", "update");
        insertPagePriviledge("bob", "Home", "read");
        insertPagePriviledge("charlie", "Home", "create");
        insertPagePriviledge("charlie", "Home", "read");
        insertPagePriviledge("charlie", "Home", "update");

        // Create Priviledge For About
        insertPagePriviledge("bob", "About", "read");
        insertPagePriviledge("bob", "About", "update");
        insertPagePriviledge("charlie", "About", "read");
        insertPagePriviledge("alice", "About", "create");
        insertPagePriviledge("alice", "About", "read");
        insertPagePriviledge("alice", "About", "update");

        // Create Priviledge For Contact
        insertPagePriviledge("charlie", "Contact", "read");
        insertPagePriviledge("charlie", "Contact", "update");
        insertPagePriviledge("alice", "Contact", "read");
        insertPagePriviledge("alice", "Contact", "create");
        insertPagePriviledge("bob", "Contact", "read");
        insertPagePriviledge("bob", "Contact", "update");

        // Create Priviledge For Preferences
        insertPagePriviledge("alice", "Preferences", "read");
        insertPagePriviledge("alice", "Preferences", "update");
        insertPagePriviledge("bob", "Preferences", "read");
        insertPagePriviledge("charlie", "Preferences", "create");
        insertPagePriviledge("charlie", "Preferences", "read");
        insertPagePriviledge("charlie", "Preferences", "update");

        // Create Priviledge For Profile
        insertPagePriviledge("bob", "Profile", "read");
        insertPagePriviledge("bob", "Profile", "update");
        insertPagePriviledge("charlie", "Profile", "read");
        insertPagePriviledge("alice", "Profile", "create");
        insertPagePriviledge("alice", "Profile", "read");
        insertPagePriviledge("alice", "Profile", "update");

    }

    private static void insertPagePriviledge(String developerName, String pageName, String priviledgeName){
        int result;
        DeveloperDaoInterface developerDao = DeveloperDao.getInstance();
        PageDaoInterface websiteDao = PageDao.getInstance();
        PriviledgeDaoInterface priviledgeDao = PriviledgeDao.getInstance();

        int developerId = developerDao.findDeveloperByUsername(developerName).getId();
        int websiteId = websiteDao.findPageByName(pageName).getId();
        int priviledgeId = priviledgeDao.findPriviledgeByName(priviledgeName).getId();
        result = priviledgeDao.assignWebsitePriviledge(developerId, websiteId, priviledgeId);
        if (result==1){
            System.out.println(String.format("Priviledge %s for Page %s and developer %s created.", priviledgeName,
                    pageName, developerName));
        }
        else{
            System.out.println(String.format("Priviledge %s for Page %s and developer %s not created.", priviledgeName,
                    pageName, developerName));
        }
    }
}
