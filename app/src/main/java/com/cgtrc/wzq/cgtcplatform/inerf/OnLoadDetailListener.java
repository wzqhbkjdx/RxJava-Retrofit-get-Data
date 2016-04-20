package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.model.NewsDetail;

/**
 * Created by bym on 16/4/6.
 */
public interface OnLoadDetailListener {
    void onDetailSuccess(NewsDetail detailNews);
    void onFailure(String msg);
}
