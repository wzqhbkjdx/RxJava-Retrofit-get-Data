package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsItem;

import java.io.Serializable;

/**
 * Created by bym on 16/3/15.
 * 代表列表新闻和图片新闻
 */
public class NewsItem implements INewsItem,Serializable {

    private String title;
    private String newsDetailLink;
    private String picLink;
    private String pubDate;
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

    public void NewsItem() {

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
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getOriginal() {
        return original;
    }
    public void setOriginal(String original) {
        this.original = original;
    }
}
