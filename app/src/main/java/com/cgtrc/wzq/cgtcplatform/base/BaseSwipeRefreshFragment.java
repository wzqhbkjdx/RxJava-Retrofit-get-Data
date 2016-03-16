package com.cgtrc.wzq.cgtcplatform.base;

import android.support.annotation.CheckResult;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.inerf.ISwipeRefreshView;

import butterknife.Bind;

/**
 * Created by bym on 16/3/15.
 */
public abstract class BaseSwipeRefreshFragment<P extends BasePresenter>
        extends BaseFragment<P> implements ISwipeRefreshView {

    @Bind(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initViews() {
        initSwipeLayout();
    }

    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(prepareRefresh()){
                    onRefreshStarted();
                } else {
                    hideRefresh();
                }
            }
        });
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },1000);
    }

    /**
     * check data status
     * @return return true indicate it should load data really else indicate don't refresh
     */
    protected boolean prepareRefresh() {
        return true;
    }

    @Override
    public void showRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @CheckResult
    public boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    @Override
    public void getDataFinish() {
        hideRefresh();
    }

    protected abstract void onRefreshStarted();

}
