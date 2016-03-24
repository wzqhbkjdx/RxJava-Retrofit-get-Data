package com.cgtrc.wzq.cgtcplatform.model;

import io.realm.RealmObject;

/**
 * Created by bym on 16/3/24.
 * TODO:
 */
public class RealmString extends RealmObject {
    private String val;

    public RealmString() {
    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
