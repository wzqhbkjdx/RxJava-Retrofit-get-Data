package com.cgtrc.wzq.cgtcplatform.presenter;

import android.content.Context;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsModel;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsPresenter;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsView;
import com.cgtrc.wzq.cgtcplatform.inerf.OnLoadDataListener;
import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.model.NewsModel;
import com.cgtrc.wzq.cgtcplatform.net.API;

/**
 * Created by bym on 16/3/16.
 */
public class NewsDataPresenter implements INewsPresenter,OnLoadDataListener<NewsData>{

    private INewsView<NewsData> mNewsView;
    private INewsModel<NewsData> mNewsModel;

    public NewsDataPresenter(Context context, INewsView<NewsData> newsView) {
        this.mNewsView = newsView;
        mNewsModel = new NewsModel(context,this);
    }


    @Override
    public void loadNews() {
        mNewsModel.init();
        mNewsView.showProgress();
        mNewsModel.getNews(API.TYPE_LATEST);
    }

    @Override
    public void loadBefore() {
        mNewsModel.getNews(API.TYPE_BEFORE);
    }

    @Override
    public void cancelConnection() {
        mNewsModel.cancel();
    }

    @Override
    public void onDataSuccess(NewsData news) {
        mNewsView.addNews(news);
        mNewsView.hideProgress();
    }

    @Override
    public void onFailure(String msg) {
        mNewsView.hideProgress();
        mNewsView.loadFailed(msg);
    }


}
