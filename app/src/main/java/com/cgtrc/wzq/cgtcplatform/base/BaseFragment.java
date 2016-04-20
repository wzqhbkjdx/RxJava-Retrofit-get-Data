package com.cgtrc.wzq.cgtcplatform.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgtrc.wzq.cgtcplatform.inerf.IBasePresenter;

import butterknife.ButterKnife;

/**
 * Created by bym on 16/3/15.
 * TODO:destory时候的内存泄露检查
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment {

    protected View rootView;
//    protected Realm realm;
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

    protected void checkPresenterIsNull() {
        if(mPresenter == null) {
//            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    private void alwaysInit() {
        ButterKnife.bind(this,rootView);//将rootView绑定到fragment上
    }

    @Override
    public void onStart() {
        super.onStart();
//        realm = Realm.getDefaultInstance();//得到Realm数据库实例
//        realm = RealmGetter.getRealm(App.realmConfiguration);

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
//        realm.close();
    }

    public boolean isLive() {
        return getActivity() != null && ! getActivity().isDestroyed();
    }

    /**
     * 检查网络连接
     * @return
     */
    public boolean checkNetworkState () {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }

}












