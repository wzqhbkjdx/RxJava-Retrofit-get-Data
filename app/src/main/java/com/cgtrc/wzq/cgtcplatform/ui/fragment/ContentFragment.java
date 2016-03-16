package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by bym on 16/3/16.
 */
public class ContentFragment extends BaseFragment {

    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    public static final String MENU_NEWS = "news";
    public static final String MENU_PIC = "pic";
    public static final String MENU_DATA = "data";

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }
}
