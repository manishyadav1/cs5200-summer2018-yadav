package edu.northeastern.cs5200.models.widget;

public class ImageWidget extends Widget {

    private String src;

    public ImageWidget() {
        super();
    }

    public ImageWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
                       int order, String src) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.src = src;
    }

    public ImageWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order, String src) {
        super(name, width, height, cssClass, cssStyle, text, order);
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
