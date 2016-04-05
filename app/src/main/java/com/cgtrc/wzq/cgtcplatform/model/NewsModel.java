package com.cgtrc.wzq.cgtcplatform.model;

import android.content.Context;
import android.util.Log;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsModel;
import com.cgtrc.wzq.cgtcplatform.inerf.OnLoadDataListener;
import com.cgtrc.wzq.cgtcplatform.net.API;
import com.cgtrc.wzq.cgtcplatform.net.HttpMethods;
import com.cgtrc.wzq.cgtcplatform.utils.Constants;
import com.cgtrc.wzq.cgtcplatform.utils.DB;
import com.cgtrc.wzq.cgtcplatform.utils.Json;
import com.cgtrc.wzq.cgtcplatform.utils.PubDateGetter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by bym on 16/3/24.
 */
public class NewsModel implements INewsModel<NewsData> {

    private long date;
    private long lastGetTime;
    private Context context;
    public static final int GET_DURATION = 2000;
    private int LorBType;
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
    public void getNews(int LorBType) {
        this.LorBType = LorBType;
        lastGetTime = System.currentTimeMillis();
        mySubscriber = new MySubscriber();

        Map<String,String> map = new HashMap<>();
        if(LorBType == API.TYPE_LATEST) {
            String ncpubdate = String.valueOf(PubDateGetter.GetMaxPubDate(Constants.NEWSCENTER));
            String lspubdate = String.valueOf(PubDateGetter.GetMaxPubDate(Constants.LSJDHZ));
            Log.i("NewsModel",ncpubdate);
            Log.i("NewsModel",lspubdate);
            map.put(Constants.NCPUBDATE, ncpubdate);
            map.put(Constants.LSPUBDATE, lspubdate);
        } else if (LorBType == API.TYPE_BEFORE) {
            String ncpubdate = String.valueOf(PubDateGetter.GetMinPubDate(Constants.NEWSCENTER));
            String lspubdate = String.valueOf(PubDateGetter.GetMinPubDate(Constants.LSJDHZ));
            Log.i("NewsModel",ncpubdate);
            Log.i("NewsModel",lspubdate);
            map.put(Constants.NCPUBDATE, ncpubdate);
            map.put(Constants.LSPUBDATE, lspubdate);
        }

        HttpMethods.getInstance().getNewsData(mySubscriber, API.BASE_URL,LorBType,map);
    }


    private void addFooter(NewsData newsData) {
        if(LorBType == API.TYPE_BEFORE) {
//            Realm realm = RealmGetter.getRealm(App.realmConfiguration);
//            realm.beginTransaction();
//            realm.copyToRealmOrUpdate(new NewsItem(newsData.getDate(),1));
//            realm.commitTransaction();
        }
    }

    private class MySubscriber extends Subscriber<NewsData> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
                getNews(LorBType);
                return;
            }
            listener.onFailure("获取数据失败");
        }

        @Override
        public void onNext(NewsData newsData) {
            date = newsData.getDate();
//            addFooter(newsData);

            List<NewsItem> list = newsData.getNewsItems();
            Gson gson = new Gson();

            DB.realm.beginTransaction();
            for(NewsItem item : list) {
                String result = gson.toJson(item);
                RealmNewsItem realmNewsItem = Json.parseRealmNewsItem(result);
                DB.realm.copyToRealmOrUpdate(realmNewsItem);
                Log.i("NewsModel","保存数据成功!");
            }
            DB.realm.commitTransaction();



            //通过Realm为Json提供的方法,将获取到的Json字符串转换为可以保存到Realm数据库中的类


//            long date = realmNewsData.getDate();

//            Shared.save(Constants.DATE,String.valueOf(date));
            listener.onDataSuccess(newsData);
        }
    }



}
