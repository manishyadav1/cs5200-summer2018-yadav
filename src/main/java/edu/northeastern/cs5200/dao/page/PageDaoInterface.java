package edu.northeastern.cs5200.dao.page;

import edu.northeastern.cs5200.models.page.Page;

import java.util.Collection;

public interface PageDaoInterface {

    int createPageForWebsite(int websiteId, Page page);

    Collection<Page> findAllPages();

    Page findPageById(int pageId);

    Page findPageByName(String pageName);

    Collection<Page> findPagesForWebsite(int websiteId);

    int updatePage(int pageId, Page page);

    int deletePage(int pageId);
}
