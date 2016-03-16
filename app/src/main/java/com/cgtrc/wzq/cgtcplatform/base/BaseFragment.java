package com.cgtrc.wzq.cgtcplatform.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by bym on 16/3/15.
 * TODO:destory时候的内存泄露检查
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected View rootView;
    protected Realm realm;
    protected P mPresenter;
    protected int layoutId;

    protected abstract void initPresenter();

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(getLayoutId(),container,false);
            ButterKnife.bind(this,rootView);//将rootView绑定到fragment上
        }
        alwaysInit();
        initViews();
        initPresenter();
        checkPresenterIsNull();
        return rootView;
    }

    private void checkPresenterIsNull() {
        if(mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    private void alwaysInit() {
        ButterKnife.bind(this,rootView);//将rootView绑定到fragment上
    }

    @Override
    public void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();//得到Realm数据库实例
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        App.getWatcher(getActivity()).watch(this); //Application实例产生一个RefWatcher进行内存泄漏监测
        realm.close();
    }

//    public boolean isLive() {
//        return getActivity() != null && getActivity().isDestroyed();
//    }
}












