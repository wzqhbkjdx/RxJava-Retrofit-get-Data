package com.cgtrc.wzq.cgtcplatform.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.inerf.IBasePresenter;

import butterknife.Bind;

/**
 * Created by bym on 16/3/15.
 */
public abstract class BaseSwipeRefreshFragment<P extends IBasePresenter>
        extends BaseFragment<P> implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.list)
    protected RecyclerView recyclerView;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    protected void initViews() {
        initSwipeLayout();
    }

    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
    }



    public void changeProgress(final boolean refreshState) {
        if(null != swipeRefreshLayout) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(refreshState);
                }
            });
        }
    }



}
