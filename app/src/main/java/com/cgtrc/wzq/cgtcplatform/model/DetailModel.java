package com.cgtrc.wzq.cgtcplatform.model;

import android.content.Context;
import android.util.Log;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetailModel;
import com.cgtrc.wzq.cgtcplatform.inerf.OnLoadDetailListener;
import com.cgtrc.wzq.cgtcplatform.net.API;
import com.cgtrc.wzq.cgtcplatform.net.HttpMethods;
import com.cgtrc.wzq.cgtcplatform.utils.DB;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by bym on 16/3/24.
 */
public class DetailModel implements INewsDetailModel {

    private Context context;
    private MySubscriber subScriber;
    private OnLoadDetailListener listener;
    private String detailNumber;


    public DetailModel(Context context,OnLoadDetailListener listener,String number) {
        this.context = context;
        this.listener = listener;
        this.detailNumber = number;
    }

    @Override
    public void cancel() {
        if(subScriber != null) {
            if(!subScriber.isUnsubscribed()) {
                subScriber.unsubscribe();
                Log.i("NewsDetailActivity","解除绑定OnDestroy");
            }
        }
    }

    @Override
    public void getNewsDetail() {
        //先从数据库获取,获取失败的话再从网络获取
        if(getDetailFromDB(detailNumber)) {
            return;
        }
        getDetailFromNet(detailNumber);
    }


    private boolean getDetailFromDB(String newsDetailLink) {
        NewsDetail detail = DB.getByLink(newsDetailLink,NewsDetail.class);
        if(detail != null) {
            listener.onDetailSuccess(detail);
            return true;
        }
        return false;
    }

    private void getDetailFromNet(String newsDetailLink) {
        subScriber = new MySubscriber();
        HttpMethods.getInstance().getNewsDetail(subScriber, API.BASE_URL,newsDetailLink);
    }

    private class MySubscriber extends Subscriber<Map<String,String>> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            listener.onFailure("无法获取当前内容");
        }

        @Override
        public void onNext(Map<String, String> map) {
            StringBuffer sb = new StringBuffer();
            if(map.size() != 0) {
                for(String str : map.keySet()) {
                    if(str.startsWith("p") | str.startsWith("img") )
                    {
                        sb.append(map.get(str)).append("\n");
                    } else {
                        continue;
                    }
                }
            }
            NewsDetail newsDetail = new NewsDetail();
            newsDetail.setNewsDetailLink(detailNumber);
            newsDetail.setNewsDetailContent(sb.toString());

            /**
             * TODO:数据库保存数据
             */
            DB.realm.beginTransaction();
            DB.realm.copyToRealmOrUpdate(newsDetail);
            DB.realm.commitTransaction();
            listener.onDetailSuccess(newsDetail);

        }
    }

}
