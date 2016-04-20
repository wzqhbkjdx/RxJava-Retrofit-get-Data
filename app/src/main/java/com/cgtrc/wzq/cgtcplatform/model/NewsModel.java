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

import io.realm.Sort;
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
            String pbpubdate = String.valueOf(PubDateGetter.GetMaxPicPubdate(Constants.PB));
            Log.i("ncpubdate",ncpubdate);
            Log.i("lspubdate",lspubdate);
            Log.i("pbpubdate",pbpubdate);
            map.put(Constants.NCPUBDATE, ncpubdate);
            map.put(Constants.LSPUBDATE, lspubdate);
            map.put(Constants.PICBANNER, pbpubdate);
        } else if (LorBType == API.TYPE_BEFORE) {
            String ncpubdate = String.valueOf(PubDateGetter.GetMinPubDate(Constants.NEWSCENTER));
            String lspubdate = String.valueOf(PubDateGetter.GetMinPubDate(Constants.LSJDHZ));
//            String pbpubdate = String.valueOf(PubDateGetter.GetMinPicPubdate(Constants.PB));
            Log.i("ncpubdate",ncpubdate);
            Log.i("lspubdate",lspubdate);
//            Log.i("pbpubdate",pbpubdate);
            map.put(Constants.NCPUBDATE, ncpubdate);
            map.put(Constants.LSPUBDATE, lspubdate);
//            map.put(Constants.PICBANNER, pbpubdate);
        }

        HttpMethods.getInstance().getNewsData(mySubscriber, API.BASE_URL,LorBType,map);
//        HttpMethods.getInstance().getNewsData(mySubscriber, API.TEST_URL,LorBType,map);
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
            return;
        }

        @Override
        public void onError(Throwable e) {
            //下面这段注释掉的代码导致程序反复不断的发出获取数据请求
//            if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
//                getNews(LorBType);
//                return;
//            }
            e.printStackTrace();
            listener.onFailure("亲,您的刷新太快了,淡定");
        }

        @Override
        public void onNext(NewsData newsData) {
            Gson gson = new Gson();
            String dataString =gson.toJson(newsData);
            Log.i("ErrorMessage",dataString);
            /**
             * TODO:判断返回的数据的error类型
             */
            int error = newsData.getErrorType();
            if(error == Constants.ERRORLATESTDATA) {
                listener.onFailure("数据是最新的");

                return;
            } else if(error == Constants.ERRORNOMOREHISDATE) {
                listener.onFailure("没有更多历史新闻");
                return;
            } else {
                date = newsData.getDate();
//            addFooter(newsData);

                List<NewsItem> list = newsData.getNewsItems();
                List<PicBanner> banners = newsData.getPicBanners();


                //将新闻列表保存到数据库

                if(list.size() != 0) {
                    for(NewsItem item : list) {
                        String result = gson.toJson(item);
                        //通过Realm为Json提供的方法,将获取到的Json字符串转换为可以保存到Realm数据库中的类
                        RealmNewsItem realmNewsItem = Json.parseRealmNewsItem(result);
                        DB.realm.beginTransaction();
                        DB.realm.copyToRealmOrUpdate(realmNewsItem);
                        if(LorBType == API.TYPE_BEFORE) {
                            DB.realm.allObjectsSorted(RealmNewsItem.class, "pubDate", Sort.DESCENDING);
                        }

                        DB.realm.commitTransaction();

                    }
                }

                if(banners.size() != 0) {
                    for(PicBanner banner : banners) {
                        String result = gson.toJson(banner);
                        //通过Realm为Json提供的方法,将获取到的Json字符串转换为可以保存到Realm数据库中的类
                        RealmPicBanner realmPicBanner = Json.parseRealmPicBanner(result);
                        DB.realm.beginTransaction();
                        DB.realm.copyToRealmOrUpdate(realmPicBanner);
                        DB.realm.allObjectsSorted(RealmPicBanner.class, "pubDate", Sort.DESCENDING);
                        DB.realm.commitTransaction();
                    }
                }

//            long date = realmNewsData.getDate();
//            Shared.save(Constants.DATE,String.valueOf(date));
                listener.onDataSuccess(newsData);

            }



        }
    }



}
