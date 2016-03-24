package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/3/24.
 */
public interface INewsModel<T extends IBaseData> {

    void getNews(int type);

    void init();

    void cancel();
}
