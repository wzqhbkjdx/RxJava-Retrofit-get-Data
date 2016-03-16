package com.cgtrc.wzq.cgtcplatform.base;

import android.app.Activity;

import com.cgtrc.wzq.cgtcplatform.inerf.JsonData;
import com.cgtrc.wzq.cgtcplatform.net.MainFactory;

/**
 * Created by bym on 16/3/15.
 */
public class BasePresenter {

    protected Activity context;
    public static final JsonData mData = MainFactory.getJsonDataInstance();
    public BasePresenter(Activity context) {
        this.context = context;
    }
}
