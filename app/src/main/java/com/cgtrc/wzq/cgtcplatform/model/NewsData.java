package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsData;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/3/24.
 */
public class NewsData extends RealmObject implements INewsData {
    @PrimaryKey
    private String date;
    private String error;
    private String ErrorType;
    private String ErrorMessage;

    private RealmList<NewsItem> newsItems;
    private RealmList<PicBanner> banners;

    public RealmList<PicBanner> getBanners() {
        return banners;
    }

    public void setBanners(RealmList<PicBanner> banners) {
        this.banners = banners;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorType() {
        return ErrorType;
    }

    public void setErrorType(String errorType) {
        ErrorType = errorType;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RealmList<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(RealmList<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
