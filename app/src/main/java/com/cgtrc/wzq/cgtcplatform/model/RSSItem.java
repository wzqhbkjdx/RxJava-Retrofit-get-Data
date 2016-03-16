package com.cgtrc.wzq.cgtcplatform.model;

/**
 * Created by bym on 16/3/15.
 */
public class RSSItem {

    private String title;
    private String description;
    private String link;
    private String pubdate;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getPubdate() {
        return pubdate;
    }
    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    @Override
    public String toString(){
        return "RSSItem [title=" + title + ", link=" + link + ", pubdate=" + pubdate +
                ", description" + description + "]";
    }
}
