package com.cgtrc.wzq.cgtcplatform.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/3/15.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolbar();
        setupDrawer();
        initNavigationView();
//        replace(ContentFragment.MENU_NEWS);
    }

    @Override
    protected void initToolbar() {
        if(toolbar == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(toolbar);
    }

    @Override
    protected int getLayoutId() {
        layoutId = R.layout.activity_main;
        return layoutId;
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置侧滑菜单
     */
    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 初始化侧滑菜单
     */
    private void initNavigationView() {
        navView.inflateMenu(R.menu.nav_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                return true;
            }
        });
    }

    /**
     * 切换fragment
     * @param type
     */
    private void replace(String type) {
//        replaceFragment(ContentFragment.newInstance(type),type);
    }

    /**
     * 创建右上角的菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about,menu);
        return true;
    }

    /**
     * 右上角菜单的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==  R.id.action_about){
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 点击返回键的时候,如果侧滑菜单处于打开状态,则关闭侧滑菜单
     */
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
//        supportPostponeEnterTransition();

    }
}
