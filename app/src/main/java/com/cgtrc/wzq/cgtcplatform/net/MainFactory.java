package com.cgtrc.wzq.cgtcplatform.net;

import com.cgtrc.wzq.cgtcplatform.inerf.JsonData;

/**
 * Created by bym on 16/3/15.
 */
public class MainFactory {

    public static final String HOST = "http://123.57.37.128";

    public static JsonData jsonData;

    protected static final Object monitor = new Object();

    public static JsonData getJsonDataInstance() {
        synchronized(monitor){
            if(jsonData == null) {
                jsonData = new MainRetrofit().getService();
            }
            return jsonData;
        }
    }
}
