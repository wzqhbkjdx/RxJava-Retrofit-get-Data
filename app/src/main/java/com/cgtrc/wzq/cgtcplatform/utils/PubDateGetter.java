package com.cgtrc.wzq.cgtcplatform.utils;


import android.util.Log;

import com.cgtrc.wzq.cgtcplatform.model.RealmNewsItem;
import com.cgtrc.wzq.cgtcplatform.model.RealmPicBanner;

import io.realm.RealmResults;

/**
 * Created by bym on 16/4/5.
 */
public class PubDateGetter {

    public static long GetMaxPubDate(int type) {
        long pubDate = Long.valueOf(Constants.STANDERTIME);
        RealmResults<RealmNewsItem> results = DB.realm.where(RealmNewsItem.class)
                .equalTo("category",type).findAll();
        if(results.size() != 0) {
            pubDate = results.max("pubDate").longValue();
            Log.i("PubDateGetter",String.valueOf(pubDate));
        }

        return pubDate;
    }

    public static long GetMinPubDate(int type) {
        long pubDate = Long.valueOf(Constants.STANDERTIME);
        RealmResults<RealmNewsItem> results = DB.realm.where(RealmNewsItem.class)
                .equalTo("category",type).findAll();
        if(results.size() != 0) {
            pubDate = results.min("pubDate").longValue();
            Log.i("PubDateGetter",String.valueOf(pubDate));
        }

        return pubDate;
    }

    public static long GetMaxPicPubdate(int type) {
        long pubDate = Long.valueOf(Constants.STANDERTIME);
        RealmResults<RealmPicBanner> results = DB.realm.where(RealmPicBanner.class)
                .equalTo("category",type).findAll();
        if(results.size() != 0) {
            pubDate = results.max("pubDate").longValue();
            Log.i("PubDateGetter",String.valueOf(pubDate));
        }

        return pubDate;
    }

    public static long GetMinPicPubdate(int type) {
        long pubDate = Long.valueOf(Constants.STANDERTIME);
        RealmResults<RealmPicBanner> results = DB.realm.where(RealmPicBanner.class)
                .equalTo("category",type).findAll();
        if(results.size() != 0) {
            pubDate = results.min("pubDate").longValue();
            Log.i("PubDateGetter",String.valueOf(pubDate));
        }

        return pubDate;
    }




}
