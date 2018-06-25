package edu.northeastern.cs5200.query.update;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.dao.role.RoleDao;
import edu.northeastern.cs5200.dao.role.RoleDaoInterface;
import edu.northeastern.cs5200.dao.website.WebsiteDao;
import edu.northeastern.cs5200.dao.website.WebsiteDaoInterface;
import edu.northeastern.cs5200.dao.widget.WidgetDao;
import edu.northeastern.cs5200.dao.widget.WidgetDaoInterface;
import edu.northeastern.cs5200.models.page.Page;
import edu.northeastern.cs5200.models.widget.Widget;

import java.util.Collection;

public class Update {

    public static void updateWidget(){
        WidgetDaoInterface widgetDao = WidgetDao.getInstance();
        Collection<Widget> widgets;
        widgets = widgetDao.findAllWidgets();
        boolean flag = false;
        for(Widget widget: widgets){
            if(flag){
                widget.setOrder(widget.getOrder()-1);
                widgetDao.updateWidget(widget.getId(), widget);
            }
            if (widget.getName().equals("head345")){
                widget.setOrder(3);
                widgetDao.updateWidget(widget.getId(), widget);
                flag=true;
            }
        }
    }

    public static void updatePage(){
        PageDaoInterface pageDao = PageDao.getInstance();
        WebsiteDaoInterface websiteDao = WebsiteDao.getInstance();
        int websiteId = websiteDao.findWebsiteByName("CNET").getId();
        Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
        for(Page page : pages){
            page.setTitle("CNET - " + page.getTitle());
            pageDao.updatePage(page.getId(), page);
        }
    }

    public static void updateRole(){
        PageDaoInterface pageDao = PageDao.getInstance();
        DeveloperDaoInterface developerDao = DeveloperDao.getInstance();
        RoleDaoInterface roleDao = RoleDao.getInstance();

        Page page = pageDao.findPageByName("CNET - Home");
        int pageId = page.getId();
        int charlieId = developerDao.findDeveloperByUsername("charlie").getId();
        int bobId = developerDao.findDeveloperByUsername("bob").getId();
        roleDao.assignPageRole(charlieId, pageId, roleDao.findRoleByName("reviewer").getId());
        roleDao.assignPageRole(bobId, pageId, roleDao.findRoleByName("writer").getId());
        roleDao.deletePageRole(charlieId, pageId, roleDao.findRoleByName("writer").getId());
        roleDao.deletePageRole(bobId, pageId, roleDao.findRoleByName("reviewer").getId());
    }
}
