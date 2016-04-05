package com.cgtrc.wzq.cgtcplatform.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/4/5.
 */
public class RealmNewsItem extends RealmObject implements Serializable {

    @PrimaryKey
    private long pubDate;

    private String title;
    private String newsDetailLink;
    private String picLink;
    private String original;
    private String timeStamp;
    private String detailNo;
    private int category;



    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDetailNo() {
        return detailNo;
    }

    public void setDetailNo(String detailNo) {
        this.detailNo = detailNo;
    }

    public String getNewsDetailLink() {
        return newsDetailLink;
    }

    public void setNewsDetailLink(String newsDetailLink) {
        this.newsDetailLink = newsDetailLink;
    }



    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPicLink() {
        return picLink;
    }
    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }
    public long getPubDate() {
        return pubDate;
    }
    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }
    public String getOriginal() {
        return original;
    }
    public void setOriginal(String original) {
        this.original = original;
    }


}
