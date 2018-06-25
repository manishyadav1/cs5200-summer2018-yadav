package edu.northeastern.cs5200.dao.priviledge;

import edu.northeastern.cs5200.models.priviledge.Priviledge;

public interface PriviledgeDaoInterface {

    int insertPriviledge(String priviledgeName);

    Priviledge findPriviledgeByName(String priviledgeName);

    int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId);

    int assignPagePriviledge(int developerId, int pageId, int priviledgeId);

    int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId);

    int deletePagePriviledge(int developerId, int pageId, int priviledgeId);
}
