package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.model.NewsDetail;

/**
 * Created by bym on 16/4/6.
 */
public interface INewsDetailView extends IBaseView {
    void showProgress();
    void showDetail(NewsDetail detailNews);
    void hideProgress();
    void showLoadFailed(String msg);
}
