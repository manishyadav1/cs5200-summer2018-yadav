package edu.northeastern.cs5200.query.create.priviledge;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.dao.priviledge.PriviledgeDao;
import edu.northeastern.cs5200.dao.priviledge.PriviledgeDaoInterface;
import edu.northeastern.cs5200.dao.website.WebsiteDao;
import edu.northeastern.cs5200.dao.website.WebsiteDaoInterface;

public class CreateWebsitePriviledge {

    public static void createWebsitePriviledge(){
        //Insert Priviledge
        insertPriviledge("create");
        insertPriviledge("read");
        insertPriviledge("update");
        insertPriviledge("delete");

        //Insert Facebook Priviledge
        insertWebsitePriviledge("alice", "Facebook", "create");
        insertWebsitePriviledge("alice", "Facebook", "read");
        insertWebsitePriviledge("alice", "Facebook", "update");
        insertWebsitePriviledge("alice", "Facebook", "delete");
        insertWebsitePriviledge("bob", "Facebook", "read");
        insertWebsitePriviledge("bob", "Facebook", "update");
        insertWebsitePriviledge("charlie", "Facebook", "create");
        insertWebsitePriviledge("charlie", "Facebook", "read");
        insertWebsitePriviledge("charlie", "Facebook", "update");
        insertWebsitePriviledge("charlie", "Facebook", "delete");

        //Insert Twitter Priviledge
        insertWebsitePriviledge("bob", "Twitter", "create");
        insertWebsitePriviledge("bob", "Twitter", "read");
        insertWebsitePriviledge("bob", "Twitter", "update");
        insertWebsitePriviledge("bob", "Twitter", "delete");
        insertWebsitePriviledge("charlie", "Twitter", "read");
        insertWebsitePriviledge("charlie", "Twitter", "update");
        insertWebsitePriviledge("alice", "Twitter", "create");
        insertWebsitePriviledge("alice", "Twitter", "read");
        insertWebsitePriviledge("alice", "Twitter", "update");
        insertWebsitePriviledge("alice", "Twitter", "delete");

        //Insert Wikipedia Priviledge
        insertWebsitePriviledge("charlie", "Wikipedia", "create");
        insertWebsitePriviledge("charlie", "Wikipedia", "read");
        insertWebsitePriviledge("charlie", "Wikipedia", "update");
        insertWebsitePriviledge("charlie", "Wikipedia", "delete");
        insertWebsitePriviledge("alice", "Wikipedia", "read");
        insertWebsitePriviledge("alice", "Wikipedia", "update");
        insertWebsitePriviledge("bob", "Wikipedia", "create");
        insertWebsitePriviledge("bob", "Wikipedia", "read");
        insertWebsitePriviledge("bob", "Wikipedia", "update");
        insertWebsitePriviledge("bob", "Wikipedia", "delete");

        //Insert CNN Priviledge
        insertWebsitePriviledge("alice", "CNN", "create");
        insertWebsitePriviledge("alice", "CNN", "read");
        insertWebsitePriviledge("alice", "CNN", "update");
        insertWebsitePriviledge("alice", "CNN", "delete");
        insertWebsitePriviledge("bob", "CNN", "read");
        insertWebsitePriviledge("bob", "CNN", "update");
        insertWebsitePriviledge("charlie", "CNN", "create");
        insertWebsitePriviledge("charlie", "CNN", "read");
        insertWebsitePriviledge("charlie", "CNN", "update");
        insertWebsitePriviledge("charlie", "CNN", "delete");

        //Insert CNET Priviledge
        insertWebsitePriviledge("bob", "CNET", "create");
        insertWebsitePriviledge("bob", "CNET", "read");
        insertWebsitePriviledge("bob", "CNET", "update");
        insertWebsitePriviledge("bob", "CNET", "delete");
        insertWebsitePriviledge("charlie", "CNET", "read");
        insertWebsitePriviledge("charlie", "CNET", "update");
        insertWebsitePriviledge("alice", "CNET", "create");
        insertWebsitePriviledge("alice", "CNET", "read");
        insertWebsitePriviledge("alice", "CNET", "update");
        insertWebsitePriviledge("alice", "CNET", "delete");

        //Insert Gizmodo Priviledge
        insertWebsitePriviledge("charlie", "Gizmodo", "create");
        insertWebsitePriviledge("charlie", "Gizmodo", "read");
        insertWebsitePriviledge("charlie", "Gizmodo", "update");
        insertWebsitePriviledge("charlie", "Gizmodo", "delete");
        insertWebsitePriviledge("alice", "Gizmodo", "read");
        insertWebsitePriviledge("alice", "Gizmodo", "update");
        insertWebsitePriviledge("bob", "Gizmodo", "create");
        insertWebsitePriviledge("bob", "Gizmodo", "read");
        insertWebsitePriviledge("bob", "Gizmodo", "update");
        insertWebsitePriviledge("bob", "Gizmodo", "delete");

    }

    private static void insertPriviledge(String priviledgeName){
        int result;
        PriviledgeDaoInterface priviledgeDao = PriviledgeDao.getInstance();
        result = priviledgeDao.insertPriviledge(priviledgeName);
        if (result==1){
            System.out.println(String.format("Priviledge %s created.", priviledgeName));
        }
        else{
            System.out.println(String.format("Priviledge %s not created.", priviledgeName));
        }
    }

    private static void insertWebsitePriviledge(String developerName, String websiteName, String priviledgeName){
        int result;
        DeveloperDaoInterface developerDao = DeveloperDao.getInstance();
        WebsiteDaoInterface websiteDao = WebsiteDao.getInstance();
        PriviledgeDaoInterface priviledgeDao = PriviledgeDao.getInstance();

        int developerId = developerDao.findDeveloperByUsername(developerName).getId();
        int websiteId = websiteDao.findWebsiteByName(websiteName).getId();
        int priviledgeId = priviledgeDao.findPriviledgeByName(priviledgeName).getId();
        result = priviledgeDao.assignWebsitePriviledge(developerId, websiteId, priviledgeId);
        if (result==1){
            System.out.println(String.format("Priviledge %s for Website %s and developer %s created.", priviledgeName,
                    websiteName, developerName));
        }
        else{
            System.out.println(String.format("Priviledge %s for Website %s and developer %s not created.", priviledgeName,
                    websiteName, developerName));
        }
    }
}
