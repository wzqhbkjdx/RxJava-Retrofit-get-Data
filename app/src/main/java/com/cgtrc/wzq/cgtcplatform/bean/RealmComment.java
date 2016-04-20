package com.cgtrc.wzq.cgtcplatform.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by bym on 16/4/19.
 */
public class RealmComment extends RealmObject implements Serializable {

    private long userID;
    private String comment;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
