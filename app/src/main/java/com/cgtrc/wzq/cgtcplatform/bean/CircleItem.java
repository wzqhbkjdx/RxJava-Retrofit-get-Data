package com.cgtrc.wzq.cgtcplatform.bean;

import com.cgtrc.wzq.cgtcplatform.inerf.ICircleData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bym on 16/4/14.
 * TODO:根据RealmCircleItem的类型增删
 */
public class CircleItem implements ICircleData, Serializable {

    private long pubDate;
    private String title;
    private String description;
    private String content;
    private String picLinks;
    private String circleLink; //用于分享和收藏
    private int category; //技术分享的类型
    private int priceCount;
    private String headPortraitLink;

    private long userID; //这条技术分享属于哪个用户  根据该UserID可以获取该用户的相关信息
    private List<CircleComment> comments; //该技术分享的评论以及用户自己的回复 userID------->comment


}
