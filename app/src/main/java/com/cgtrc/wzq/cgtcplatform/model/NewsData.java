package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bym on 16/3/24.
 */
public class NewsData implements INewsData,Serializable {

    private long date;
    private String error;
    private String ErrorMessage;


    private List<NewsItem> newsItems;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
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
