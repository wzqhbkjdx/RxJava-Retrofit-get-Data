package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.base.Core;

import java.util.List;

/**
 * Created by bym on 16/3/16.
 */
public interface IMainView<T extends Core> extends ISwipeRefreshView {

    /**
     *  成功加载数据
     * @param data
     */
    void fillData(List<T> data);

    /**
     * 加载历史数据
     * @param data
     */
    void appendMoreDataToView(List<T> data);

    /**
     * no more data for show and this condition is hard to appear,it need you scroll main view long time
     *  I think it has no body do it like this ,even though，I deal this condition also, In case someone does it.
     */
    void hasNoMoreData();

    /**
     * show change log info in a dialog
     * @param assertFileName the name of local html file like "changelog.html"
     */
    void showChangeLogInfo(String assertFileName);

}
