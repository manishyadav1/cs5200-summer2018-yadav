package edu.northeastern.cs5200.dao.widget;

import edu.northeastern.cs5200.models.widget.*;

import java.util.Collection;

public interface WidgetDaoInterface {

    int createHeadingWidgetForPage(int pageId, HeadingWidget widget);

    int createHtmlWidgetForPage(int pageId, HtmlWidget htmlWidget);

    int createImageWidgetForPage(int pageId, ImageWidget imageWidget);

    int createYouTubeWidgetForPage(int pageId, YouTubeWidget youTubeWidget);

    Collection<Widget> findAllWidgets();

    Widget findWidgetById(int widgetId);

    Collection<Widget> findWidgetsForPage(int pageId);

    int updateWidget(int widgetId, Widget widget);

    int deleteWidget(int widgetId);
}
