package com.cgtrc.wzq.cgtcplatform.bean;

import com.cgtrc.wzq.cgtcplatform.inerf.IBaseData;

import java.util.List;

/**
 * Created by bym on 16/4/14.
 */
public class CircleData implements IBaseData {

    private long date;
    private int errorType;

    private List<CircleItem> circleItems;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public List<CircleItem> getCircleItems() {
        return circleItems;
    }

    public void setCircleItems(List<CircleItem> circleItems) {
        this.circleItems = circleItems;
    }
}
