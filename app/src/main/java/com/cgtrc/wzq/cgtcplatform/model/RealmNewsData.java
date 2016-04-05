package com.cgtrc.wzq.cgtcplatform.model;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/4/5.
 */
public class RealmNewsData extends RealmObject implements Serializable {
    @PrimaryKey
    private long date;

    private String error;
    private String ErrorMessage;

    private RealmList<RealmNewsItem> RealmNewsItems;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

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

    public RealmList<RealmNewsItem> getRealmNewsItems() {
        return RealmNewsItems;
    }

    public void setRealmNewsItems(RealmList<RealmNewsItem> realmNewsItems) {
        RealmNewsItems = realmNewsItems;
    }
}
