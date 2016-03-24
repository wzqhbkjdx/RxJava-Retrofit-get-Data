package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.model.NewsData;

/**
 * Created by bym on 16/3/24.
 */
public interface OnLoadDataListener<T extends IBaseData> {
    void onDataSuccess(NewsData news);
    void onFailure(String msg);
}
