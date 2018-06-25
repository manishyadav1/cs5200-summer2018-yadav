package edu.northeastern.cs5200.query.delete;

import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.dao.website.WebsiteDao;
import edu.northeastern.cs5200.dao.website.WebsiteDaoInterface;
import edu.northeastern.cs5200.dao.widget.WidgetDao;
import edu.northeastern.cs5200.dao.widget.WidgetDaoInterface;
import edu.northeastern.cs5200.models.page.Page;
import edu.northeastern.cs5200.models.widget.Widget;

import java.util.Collection;
import java.util.Date;

public class Delete {

    public static void deleteWidget(){
        PageDaoInterface pageDao = PageDao.getInstance();
        Page page = pageDao.findPageByName("Contact");
        WidgetDaoInterface widgetDao = WidgetDao.getInstance();
        Collection<Widget> widgets = widgetDao.findWidgetsForPage(page.getId());
        Widget maxWidget=null;
        int maxOrder = Integer.MIN_VALUE;
        for(Widget widget : widgets){
            if(widget.getOrder() > maxOrder){
                maxOrder = widget.getOrder();
                maxWidget = widget;
            }
        }
        widgetDao.deleteWidget(maxWidget.getId());
    }

    public static void deletePage(){
        PageDaoInterface pageDao = PageDao.getInstance();
        Date maxTime = new Date(Long.MIN_VALUE);
        Page maxTimePage = null;
        for(Page page : pageDao.findAllPages()){
            if(page.getUpdated().after(maxTime)){
                maxTime=page.getUpdated();
                maxTimePage = page;
            }
        }
        pageDao.deletePage(maxTimePage.getId());
    }

    public static void deleteWebsite(){
        WebsiteDaoInterface websiteDao = WebsiteDao.getInstance();
        websiteDao.deleteWebsite(websiteDao.findWebsiteByName("CNET").getId());
    }
}
