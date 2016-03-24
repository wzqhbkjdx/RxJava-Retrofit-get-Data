package com.cgtrc.wzq.cgtcplatform.model;

import android.content.Context;
import android.util.Log;

import com.cgtrc.wzq.cgtcplatform.App;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsModel;
import com.cgtrc.wzq.cgtcplatform.inerf.OnLoadDataListener;
import com.cgtrc.wzq.cgtcplatform.net.API;
import com.cgtrc.wzq.cgtcplatform.net.HttpMethods;
import com.cgtrc.wzq.cgtcplatform.utils.RealmGetter;

import io.realm.Realm;
import rx.Subscriber;

/**
 * Created by bym on 16/3/24.
 */
public class NewsModel implements INewsModel<NewsData> {

    private String date;
    private long lastGetTime;
    private Context context;
    public static final int GET_DURATION = 2000;
    private int type;
    private boolean isBefore;
    private MySubscriber mySubscriber;
    private OnLoadDataListener<NewsData> listener;


    public NewsModel(Context context, OnLoadDataListener<NewsData> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void init() {
        isBefore = false;
    }

    @Override
    public void cancel() {
        if(mySubscriber != null) {
            if(!mySubscriber.isUnsubscribed()) {
                mySubscriber.unsubscribe();
                Log.i("NewFragment","解除绑定OnDestroy");
            }
        }
    }

    @Override
    public void getNews(int type) {
        this.type = type;
        lastGetTime = System.currentTimeMillis();
        mySubscriber = new MySubscriber();
        HttpMethods.getInstance().getNewsData(mySubscriber, API.BASE_URL,type);
    }


    private void addFooter() {
        if(type == API.TYPE_BEFORE) {
            Realm realm = RealmGetter.getRealm(App.realmConfiguration);
            realm.beginTransaction();
//            realm.copyToRealmOrUpdate()
        }
    }

    private class MySubscriber extends Subscriber<NewsData> {


        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
                getNews(type);
                return;
            }
            listener.onFailure("load News failed!");
        }

        @Override
        public void onNext(NewsData newsData) {

            listener.onDataSuccess(newsData);
            addFooter();
        }
    }



}
