package com.cgtrc.wzq.cgtcplatform.inerf;

import java.util.List;

/**
 * Created by bym on 16/3/18.
 * fragment or activity need to implement this to show news list.
 */
public interface INewsView<T extends IBaseData> extends IBaseView {
    void showProgress();
    void addNews(List<T> news);
    void hideProgress();
    void loadFailed(String msg);
}
