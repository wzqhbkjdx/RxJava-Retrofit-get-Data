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
    private String pubdate;
    private String type;

    @Override
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
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

    @Override
    public boolean isHeader() { //这个数据结构服务器那边也需要同步更改,从而才能适配
        if(type.equals(DataCategory.图片新闻)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isNormalNews() { //这个数据结构服务器那边也需要同步更改,从而才能适配
        if(type.equals(DataCategory.行业动态) || type.equals(DataCategory.行业新闻) || type.equals(DataCategory.长知识)) {
            return true;
        } else {
            return false;
        }
    }
}
