package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsData;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/3/15.
 * 代表列表新闻和图片新闻
 */
public class RSSItem extends RealmObject implements Serializable,INewsData {

    @PrimaryKey
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String type;
    private String original;
    private String picLink;


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

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }
}
