package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/4/12.
 */
public interface ICircleView<T extends IBaseData> extends IBaseView {

    void showProgress();
    void addNews(T news);
    void hideProgress();
    void loadFailed(String msg);



}
