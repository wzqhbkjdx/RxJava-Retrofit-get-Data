package com.cgtrc.wzq.cgtcplatform.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/3/17.
 * news item in top banner
 */
public class PicBanner extends RealmObject implements Serializable {  //保存到本地,还是用Serializable

    @PrimaryKey
    private String title;
    private String link;
    private String pubDate;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getLink() {
        return link;
    }


    public String getTitle() {
        return title;
    }


    public String getPubDate() {
        return pubDate;
    }


    public String getDescription() {
        return description;
    }

}
