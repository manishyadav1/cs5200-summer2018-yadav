package edu.northeastern.cs5200.query.create.widget;

import edu.northeastern.cs5200.dao.page.PageDao;
import edu.northeastern.cs5200.dao.page.PageDaoInterface;
import edu.northeastern.cs5200.dao.widget.WidgetDao;
import edu.northeastern.cs5200.dao.widget.WidgetDaoInterface;
import edu.northeastern.cs5200.models.widget.HeadingWidget;
import edu.northeastern.cs5200.models.widget.HtmlWidget;
import edu.northeastern.cs5200.models.widget.ImageWidget;
import edu.northeastern.cs5200.models.widget.YouTubeWidget;

public class CreateWidget {

    public static void createWidget(){

        // Create head123
        createHeading("head123", 0, 0, "classA","StyleB", "Welcome",
                0, 4, "Home");

        // Create post234
        createHtml("post234", 1,1, "classX", "classY", "<p>Lorem</p>",
                0, "<p>Lorem</p>", "About");

        // Create head345
        createHeading("head345", 1, 1, "classM","StyleN", "Hi",
                1, 2, "Contact");

        // Create intro456
        createHtml("intro456", 2,2, "classO", "classP", "<h1>Hi</h1>",
                2, "<h1>Hi</h1>", "Contact");

        // Create image345
        createImage("image345", 50, 100, "class1", "class2", "", 3,
                "/img/567.png", "Contact");

        // Create video456
        createYouTube("video456", 400, 300, "class3", "class5", "", 0,
                "https://youtu.be/h67VX51QXiQ", true, true, "Preferences");
    }

    private static void createHeading(String name, int width, int height, String cssClass, String cssStyle, String text,
                                      int order, int size, String widgetPage){
        WidgetDaoInterface widgetDao = WidgetDao.getInstance();
        PageDaoInterface pageDao = PageDao.getInstance();
        int result;
        HeadingWidget headingWidget = new HeadingWidget(name, width, height, cssClass, cssStyle, text, order, size);
        int pageId = pageDao.findPageByName(widgetPage).getId();
        result = widgetDao.createHeadingWidgetForPage(pageId, headingWidget);
        if(result==1)
            System.out.println(String.format("Widget %s successfully created.", name));
        else
            System.out.println(String.format("Not able to create Widget %s.", name));
    }

    private static void createHtml(String name, int width, int height, String cssClass, String cssStyle, String text,
                                   int order, String html, String widgetPage){
        WidgetDaoInterface widgetDao = WidgetDao.getInstance();
        PageDaoInterface pageDao = PageDao.getInstance();
        int result;
        HtmlWidget htmlWidget = new HtmlWidget(name, width, height, cssClass, cssStyle, text, order, html);
        int pageId = pageDao.findPageByName(widgetPage).getId();
        result = widgetDao.createHtmlWidgetForPage(pageId, htmlWidget);
        if(result==1)
            System.out.println(String.format("Widget %s successfully created.", name));
        else
            System.out.println(String.format("Not able to create Widget %s.", name));
    }

    private static void createImage(String name, int width, int height, String cssClass, String cssStyle, String text,
                                   int order, String src, String widgetPage){
        WidgetDaoInterface widgetDao = WidgetDao.getInstance();
        PageDaoInterface pageDao = PageDao.getInstance();
        int result;
        ImageWidget imageWidget = new ImageWidget(name, width, height, cssClass, cssStyle, text, order, src);
        int pageId = pageDao.findPageByName(widgetPage).getId();
        result = widgetDao.createImageWidgetForPage(pageId, imageWidget);
        if(result==1)
            System.out.println(String.format("Widget %s successfully created.", name));
        else
            System.out.println(String.format("Not able to create Widget %s.", name));
    }

    private static void createYouTube(String name, int width, int height, String cssClass, String cssStyle, String text,
                                    int order, String url, Boolean shareble, Boolean expandable, String widgetPage){
        WidgetDaoInterface widgetDao = WidgetDao.getInstance();
        PageDaoInterface pageDao = PageDao.getInstance();
        int result;
        YouTubeWidget youTubeWidget = new YouTubeWidget(name, width, height, cssClass, cssStyle, text, order, url,
                shareble, expandable);
        int pageId = pageDao.findPageByName(widgetPage).getId();
        result = widgetDao.createYouTubeWidgetForPage(pageId, youTubeWidget);
        if(result==1)
            System.out.println(String.format("Widget %s successfully created.", name));
        else
            System.out.println(String.format("Not able to create Widget %s.", name));
    }
}
