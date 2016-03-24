package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/3/24.
 */
public interface INewsDetailModel<T extends IBaseData, D extends INewsDetail> {
    void cancel();
    void getNewsDetail(T newsItem);

}
