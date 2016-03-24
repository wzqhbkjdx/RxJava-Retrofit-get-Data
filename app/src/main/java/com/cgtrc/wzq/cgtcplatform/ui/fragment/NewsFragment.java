package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.adapter.MainListAdapter;
import com.cgtrc.wzq.cgtcplatform.base.BaseSwipeRefreshFragment;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsView;
import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.presenter.NewsDataPresenter;
import com.cgtrc.wzq.cgtcplatform.ui.activity.MainActivity;
import com.cgtrc.wzq.cgtcplatform.utils.UiUtils;

/**
 * Created by bym on 16/3/16.
 * TODO:添加网络请求的时候,在该view destory 或者用户点击back键后,应该取消该网络请求
 */
public class NewsFragment extends BaseSwipeRefreshFragment<NewsDataPresenter> implements INewsView<NewsData> {


    private MainListAdapter adapter;
//    private ConvenientBanner banner; //首行的图片轮转控件 先不加banner
    private LinearLayoutManager layoutManager;




    @Override
    public void onDestroyView() {
        /**
         * TODO:在这里添加网络请求的cancel方法
         */
        mPresenter.cancelConnection();
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
                /**
                 * TODO:item的点击事件,进入新闻详情页面
                 */
            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()
                        && adapter.isHasFooter()) {
                    /**
                     * TODO:newDataPresenter 加载历史数据
                     */
                }
            }
        });
    }

    @Override
    protected void initData() {
        /**
         * TODO:初始化Banner
         */
        initBanner();
        onRefresh();
    }

    private void initBanner() {
        /**
         * TODO:初始化Banner
         */
    }

    @Override
    public void showProgress() {
        changeProgress(true);
    }

    @Override
    public void addNews(NewsData news) {
        adapter.addNews(news);
    }

    @Override
    public void hideProgress() {
        changeProgress(false);
    }

    @Override
    public void loadFailed(String msg) {
        if (isLive()) {
            UiUtils.showSnack(((MainActivity) getActivity()).getDrawerLayout(), R.string.load_fail);
        }
    }

    @Override
    protected void initPresenter() {
//        mPresenter = new NewsDataPresenter(getActivity());z
    }

    @Override
    public void onRefresh() {
        /**
         * TODO:presenter.loadNews()
         */

    }


}
