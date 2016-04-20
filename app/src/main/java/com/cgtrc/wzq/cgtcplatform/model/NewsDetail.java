package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetail;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/3/24.
 *
 */
public class NewsDetail extends RealmObject implements INewsDetail,Serializable {
    @PrimaryKey
    private String newsDetailLink;

    private String newsDetailContent;

    public String getNewsDetailLink() {
        return newsDetailLink;
    }

    public void setNewsDetailLink(String newsDetailLink) {
        this.newsDetailLink = newsDetailLink;
    }

    public String getNewsDetailContent() {
        return newsDetailContent;
    }

    public void setNewsDetailContent(String newsDetailContent) {
        this.newsDetailContent = newsDetailContent;
    }
}
