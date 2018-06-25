package edu.northeastern.cs5200.dao.website;

import edu.northeastern.cs5200.models.website.Website;

import java.util.Collection;

public interface WebsiteDaoInterface {

    int createWebsiteForDeveloper(int developerId, Website website);

    Collection<Website> findAllWebsites();

    Collection<Website> findWebsitesForDeveloper(int developerId);

    Website findWebsiteById(int websiteId);

    Website findWebsiteByName(String websiteName);

    int updateWebsite(int websiteId, Website website);

    int deleteWebsite(int websiteId);
}
