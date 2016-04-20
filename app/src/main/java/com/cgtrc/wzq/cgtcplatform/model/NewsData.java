package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bym on 16/3/24.
 */
public class NewsData implements INewsData,Serializable {

    private long date;
    private int errorType;

    private List<NewsItem> newsItems;
    private List<PicBanner> picBanners;

    public NewsData() {
        this.newsItems = new ArrayList<NewsItem>();
        this.picBanners = new ArrayList<PicBanner>();

    }

    public List<PicBanner> getPicBanners() {
        return picBanners;
    }

    public void setPicBanners(List<PicBanner> picBanners) {
        this.picBanners = picBanners;
    }



    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
