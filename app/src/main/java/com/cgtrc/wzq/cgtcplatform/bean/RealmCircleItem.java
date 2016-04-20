package com.cgtrc.wzq.cgtcplatform.bean;

import com.cgtrc.wzq.cgtcplatform.inerf.ICircleData;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/4/19.
 */
public class RealmCircleItem extends RealmObject implements Serializable,ICircleData {

    @PrimaryKey
    private long pubDate;

    private String title;
    private String description;
    private String content;
    private String picLinks;
    private String circleLink; //用于分享和收藏
    private String category; //技术分享的类型
    private int priceCount;
    private String headPortraitLink;
    private RealmList<RealmComment> commentList;
    private String userName;
    private long userID; //这条技术分享属于哪个用户  根据该UserID可以获取该用户的相关信息

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getHeadPortraitLink() {
        return headPortraitLink;
    }

    public void setHeadPortraitLink(String headPortraitLink) {
        this.headPortraitLink = headPortraitLink;
    }

    public int getPriceCount() {
        return priceCount;
    }

    public void setPriceCount(int priceCount) {
        this.priceCount = priceCount;
    }



    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicLinks() {
        return picLinks;
    }

    public void setPicLinks(String picLinks) {
        this.picLinks = picLinks;
    }

    public String getCircleLink() {
        return circleLink;
    }

    public void setCircleLink(String circleLink) {
        this.circleLink = circleLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public RealmList<RealmComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(RealmList<RealmComment> commentList) {
        this.commentList = commentList;
    }
}
