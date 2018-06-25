package edu.northeastern.cs5200.models.widget;

public class HtmlWidget extends Widget {

    private String html;

    public HtmlWidget() {
        super();
    }

    public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
                      int order, String html) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.html = html;
    }

    public HtmlWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order, String html) {
        super(name, width, height, cssClass, cssStyle, text, order);
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
