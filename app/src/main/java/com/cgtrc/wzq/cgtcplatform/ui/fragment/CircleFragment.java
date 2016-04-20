package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.adapter.CircleAdapter;
import com.cgtrc.wzq.cgtcplatform.base.BaseSwipeRefreshFragment;
import com.cgtrc.wzq.cgtcplatform.bean.CircleData;
import com.cgtrc.wzq.cgtcplatform.inerf.ICircleView;
import com.cgtrc.wzq.cgtcplatform.presenter.CirclePresenter;

import butterknife.Bind;

/**
 * Created by bym on 16/4/12.
 */
public class CircleFragment extends BaseSwipeRefreshFragment<CirclePresenter> implements ICircleView<CircleData> {

    private CircleAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Bind(R.id.list)
    protected RecyclerView recyclerView;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }



    @Override
    protected void initViews() {
        super.initViews();
        Context context = getActivity();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CircleAdapter(context);

        adapter.setListener(new CircleAdapter.IClickCircleItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {

            }

            @Override
            public void onHeadIvClick(long userID, int position) {

            }

            @Override
            public void onPriceClick(long userID, int position) {

            }

            @Override
            public void onStarClick(long userID, int position) {

            }

            @Override
            public void onShareClick(int position) {

            }

            @Override
            public void onCommentClick(long userID, int position) {

            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        layoutId = R.layout.fragment_circle;
        return layoutId;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void addNews(CircleData news) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void onRefresh() {

    }
}
