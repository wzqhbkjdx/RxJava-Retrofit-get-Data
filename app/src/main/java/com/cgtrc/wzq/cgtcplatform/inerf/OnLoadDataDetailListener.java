package com.cgtrc.wzq.cgtcplatform.inerf;

/**
 * Created by bym on 16/3/24.
 */
public interface OnLoadDataDetailListener<T extends INewsDetail> {
    void onDeatilSuccess(T detailNews);
    void onFailure(String msg);

}
