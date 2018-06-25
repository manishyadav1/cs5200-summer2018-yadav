package edu.northeastern.cs5200.query.create.website;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.dao.website.WebsiteDao;
import edu.northeastern.cs5200.dao.website.WebsiteDaoInterface;
import edu.northeastern.cs5200.models.website.Website;

import java.util.Date;

public class CreateWebsite {

    private static Date getDate(){
        return new Date();
    }

   public static void createWebsite(){

       //Create Facebook
       createWebsite("Facebook", "an online social media and social networking service",
               getDate(), getDate(), 1234234, "alice");

       //Create Twitter
       createWebsite("Twitter", "an online news and social networking service",
               getDate(), getDate(),4321543, "bob");

       //Create Wikipedia
       createWebsite("Wikipedia", "a free online encyclopedia",
               getDate(), getDate(), 3456654, "charlie");

       //Create CNN
       createWebsite("CNN", "an American basic cable and satellite television news channel",
               getDate(), getDate(), 6543345, "alice");

       //Create CNET
       createWebsite("CNET", "an American media website that publishes reviews, news, " +
                       "articles, blogs, podcasts and videos on technology and consumer electronics",
               getDate(), getDate(), 5433455, "bob");

       //Create Gizmodo
       createWebsite("Gizmodo", "a design, technology, science and science fiction website " +
                       "that also writes articles on politics", getDate(), getDate(), 4322345, "charlie");

   }

   private static void createWebsite(String websiteName, String websiteDescription, Date createDate, Date updateDate,
                                     int websiteVisits, String websiteDeveloper){
       WebsiteDaoInterface websiteDao = WebsiteDao.getInstance();
       DeveloperDaoInterface developerDao = DeveloperDao.getInstance();
       int result;
       Website website = new Website(websiteName, websiteDescription, createDate, updateDate, websiteVisits);
       int developerId = developerDao.findDeveloperByUsername(websiteDeveloper).getId();
       result = websiteDao.createWebsiteForDeveloper(developerId, website);
       if(result==1)
           System.out.println(String.format("Website %s successfully created.", websiteName));
       else
           System.out.println(String.format("Not able to create Website %s.", websiteName));
   }

}
