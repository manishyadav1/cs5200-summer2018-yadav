package edu.northeastern.cs5200.query.create.page;

import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.dao.website.WebsiteDao;
import edu.northeastern.cs5200.dao.website.WebsiteDaoInterface;
import edu.northeastern.cs5200.dao.widget.WidgetDao;
import edu.northeastern.cs5200.models.page.Page;
import edu.northeastern.cs5200.models.website.Website;

import java.util.Date;

public class CreatePage {

    private static Date getDate(){return new Date();}

    public static void createPage(){

        // Create Home
        createPage("Home", "Landing page", getDate(), getDate(), 123434,
                "CNET");

        // Create About
        createPage("About", "Website description", getDate(), getDate(), 234545,
                "Gizmodo");

        // Create Contact
        createPage("Contact", "Addresses, phones, and contact info", getDate(), getDate(),
                345656, "Wikipedia");

        // Create Preferences
        createPage("Preferences", "Where users can configure their preferences", getDate(), getDate(),
                456776, "CNN");

        // Create Profile
        createPage("Profile", "Users can configure their personal information", getDate(), getDate(),
                567878, "CNET");
    }

    private static void createPage(String pageTitle, String pageDescription, Date pageCreated, Date pageUpdated,
                                   int pageViews, String pageWebiste){
        PageDaoInterface pageDao = PageDao.getInstance();
        WebsiteDaoInterface websiteDao = WebsiteDao.getInstance();
        int result;
        Page page = new Page(pageTitle, pageDescription, pageCreated, pageUpdated, pageViews);
        int websiteId = websiteDao.findWebsiteByName(pageWebiste).getId();
        result = pageDao.createPageForWebsite(websiteId, page);
        if(result==1)
            System.out.println(String.format("Page %s successfully created.", pageTitle));
        else
            System.out.println(String.format("Not able to create Page %s.", pageTitle));
    }
}
