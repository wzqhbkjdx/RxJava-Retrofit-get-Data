package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/3/15.
 */
public interface ISwipeRefreshView extends IBaseView {
    void getDataFinish();
    void showEmptyView();
    void showErrorView(Throwable throwable);
    void showRefresh();
    void hideRefresh();
}
