package com.cgtrc.wzq.cgtcplatform.presenter;

import android.content.Context;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetailModel;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetailPresenter;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetailView;
import com.cgtrc.wzq.cgtcplatform.inerf.OnLoadDetailListener;
import com.cgtrc.wzq.cgtcplatform.model.DetailModel;
import com.cgtrc.wzq.cgtcplatform.model.NewsDetail;

/**
 * Created by bym on 16/4/6.
 */
public class NewsDetailPresenter implements INewsDetailPresenter, OnLoadDetailListener {

    private INewsDetailModel newsDetailModel;
    private INewsDetailView newsDetailView;
    private String detailNumber;

    public NewsDetailPresenter(INewsDetailView newsDetailView, Context context,String number) {
        this.newsDetailModel = new DetailModel(context,this,number);
        this.newsDetailView = newsDetailView;
        this.detailNumber = number;
    }


    @Override
    public void loadNewsDetail() {
        newsDetailView.showProgress();
        newsDetailModel.getNewsDetail();
    }

    @Override
    public void cancelConnection() {
        newsDetailModel.cancel();
    }

    @Override
    public void onDetailSuccess(NewsDetail detailNews) {
        newsDetailView.showDetail(detailNews);
    }

    @Override
    public void onFailure(String msg) {
        newsDetailView.showLoadFailed(msg);
        newsDetailView.hideProgress();
    }
}
