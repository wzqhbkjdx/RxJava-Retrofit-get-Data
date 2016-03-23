package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseFragment;
import com.cgtrc.wzq.cgtcplatform.base.BaseSwipeRefreshFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by bym on 16/3/16.
 */
public class ContentFragment extends BaseFragment {

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    private List<BaseSwipeRefreshFragment> fragments = new ArrayList<>();
    private List<String> titles;
    private NewsTabPagerAdapter adapter;

    @Override
    protected void initViews() {
        adapter = new NewsTabPagerAdapter(getChildFragmentManager());
        initFragments();
        viewPager.setAdapter(adapter);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewPager);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                scrollToTop(fragments.get(tab.getPosition()).getRecyclerView());
            }
        });
    }



    private void initFragments() {
        String[] newsTitles = new String[] {
                getString(R.string.news),
                getString(R.string.store),
                getString(R.string.data),
                getString(R.string.circle)
        };
        titles = Arrays.asList(newsTitles);

        fragments.add(new NewsFragment());//加入新文的fragment
        fragments.add(new NewsFragment());//加入商城的fragment
        fragments.add(new NewsFragment());//加入数据的fragment
        fragments.add(new NewsFragment());//加入圈子的fragment

        adapter.setFragments(fragments,titles);
    }

    private void scrollToTop(RecyclerView recyclerView) {
        if(recyclerView != null) {
            int lastPosition;
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            lastPosition = manager.findLastVisibleItemPosition();
            if(lastPosition < SMOOTHSCROLL_TOP_POSITION) {
                recyclerView.smoothScrollToPosition(0);
            } else {
                recyclerView.scrollToPosition(0);
            }
        }

    }

    @Override
    protected void initData() {

    }
    @Override
    protected int getLayoutId() {
        layoutId = R.layout.fragment_layout;
        return layoutId;
    }




    @Override
    protected void initPresenter() {

    }

    @Override
    protected void checkPresenterIsNull() {

    }

    public static class NewsTabPagerAdapter extends FragmentPagerAdapter {
        private List<BaseSwipeRefreshFragment> fragments;
        private List<String> titles;

        public NewsTabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setFragments(List<BaseSwipeRefreshFragment> fragments, List<String> titles) {
            this.fragments = fragments;
            this.titles = titles;
        }

        public void addFragment(BaseSwipeRefreshFragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
