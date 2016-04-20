package com.cgtrc.wzq.cgtcplatform.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cgtrc.wzq.cgtcplatform.R;

import butterknife.Bind;

/**
 * Created by bym on 16/3/15.
 * TODO: 友盟数据统计
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected int layoutId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏全透明尝试
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }

        initView();
        initData();
    }

    /**
     * 子类覆盖该方法,必须调用父类 super.initView()
     */
    protected void initView() {
        getLayoutId();
        setContentView(layoutId);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);//友盟数据统计
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //是否需要对Activity进行内存泄露监测,需要进一步考量
//        ProjectApp.getWatcher(getActivity()).watch(this); //Application实例产生一个RefWatcher进行内存泄漏监测
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment,tag);

        transaction.commit();
    }

    abstract protected void initToolbar();

    protected abstract int getLayoutId();

    protected abstract void initData();
}











