package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.adapter.MainListAdapter;
import com.cgtrc.wzq.cgtcplatform.base.BaseSwipeRefreshFragment;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsView;
import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.presenter.NewsDataPresenter;
import com.cgtrc.wzq.cgtcplatform.ui.activity.MainActivity;
import com.cgtrc.wzq.cgtcplatform.ui.activity.NewsDetailActivity;
import com.cgtrc.wzq.cgtcplatform.utils.Constants;
import com.cgtrc.wzq.cgtcplatform.utils.UiUtils;

import butterknife.Bind;

/**
 * Created by bym on 16/3/16.
 *
 */
public class NewsFragment extends BaseSwipeRefreshFragment<NewsDataPresenter> implements INewsView<NewsData> {


    private MainListAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Bind(R.id.list)
    protected RecyclerView recyclerView;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onDestroyView() {

        mPresenter.cancelConnection(); //取消网络请求
        super.onDestroyView();

    }

    @Override
    protected int getLayoutId() {
        return layoutId = R.layout.fragment_recycler ;
    }

    @Override
    protected void initViews() {
        super.initViews();
        Context context = getActivity();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainListAdapter(context);
        adapter.setIClickItemListener(new MainListAdapter.IClickMainItemListener() {

            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                //进入新闻详情页
                if(viewHolder instanceof MainListAdapter.ViewHolder) {
                    MainListAdapter.ViewHolder holder = (MainListAdapter.ViewHolder) viewHolder;
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra(Constants.LINK, holder.newsItem.getNewsDetailLink());
                    intent.putExtra(Constants.PICLINK,holder.newsItem.getPicLink());
                    intent.putExtra(Constants.NUMBER,String.valueOf(holder.newsItem.getPubDate()));
                    intent.putExtra(Constants.TITLE,holder.newsItem.getTitle());
//                    startActivity(intent);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            holder.mImage, getString(R.string.shared_img));
                    ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());

                    holder.mTitle.setTextColor(MainListAdapter.textGrey);
                }

            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {

                    mPresenter.loadBefore();
                }
            }
        });
    }

    /**
     * BaseFragment的方法,在
     * BaseFragment中onCreatActivity调用
     */
    @Override
    protected void initData() {
        initBanner();
        onRefresh();
    }


    private void initBanner() {

    }

    /**
     * INewsView接口的方法,在presenter中调用
     */
    @Override
    public void showProgress() {
        changeProgress(true);
    }

    /**
     * INewsView接口的方法,在presenter中调用
     * @param news
     */
    @Override
    public void addNews(NewsData news) {
        adapter.addNews(news);
    }

    /**
     * INewsView接口的方法,在presenter中调用
     */
    @Override
    public void hideProgress() {
        changeProgress(false);
    }

    /**
     * INewsView接口的方法,在presenter中调用
     * @param msg
     */
    @Override
    public void loadFailed(String msg) {
        if (isLive()) {
//            UiUtils.showSnack(((MainActivity) getActivity()).getDrawerLayout(), R.string.load_fail);
            UiUtils.showSnackByTag(((MainActivity) getActivity()).getDrawerLayout(),msg);
        }
    }

    /**
     * BaseFragment中的方法,在BaseFragment中的onCreateView中调用
     */
    @Override
    protected void initPresenter() {
        mPresenter = new NewsDataPresenter(getActivity(),this);
    }

    /**
     * 实现BaseSwipeRefreshFragment的onRefresh方法
     * 同时在BaseFragment中的onCreateActivity中调用
     */
    @Override
    public void onRefresh() {
        if(checkNetworkState()) { //检查网络连接
            mPresenter.loadNews();
        } else {
            loadFailed("没有网络连接,请稍后再试!");
            hideProgress();
        }


    }


}
