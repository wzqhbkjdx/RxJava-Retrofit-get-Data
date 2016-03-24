package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/3/24.
 */
public interface INewsPresenter extends IBasePresenter {
    void loadNews();
    void loadBefore();
    void cancelConnection();

}
