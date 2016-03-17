package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseSwipeRefreshFragment;
import com.cgtrc.wzq.cgtcplatform.presenter.NewsDataPresenter;

import butterknife.Bind;

/**
 * Created by bym on 16/3/16.
 */
public class NewsFragment extends BaseSwipeRefreshFragment<NewsDataPresenter> {

    @Bind(R.id.list)
    protected RecyclerView recyclerView;

    @Override
    protected void onRefreshStarted() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showErrorView(Throwable throwable) {

    }
}
