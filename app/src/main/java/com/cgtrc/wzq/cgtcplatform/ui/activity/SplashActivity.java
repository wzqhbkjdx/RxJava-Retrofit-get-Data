package com.cgtrc.wzq.cgtcplatform.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cgtrc.wzq.cgtcplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO:连接网络从服务器获取启动图片
 */
public class SplashActivity extends Activity {
    private static final int SPLASH_DURATION = 3000;
    private static final String SPLASH = "splash";
    @Bind(R.id.splash)
    protected ImageView splash;
    private String today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN //全屏
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //没有导航栏
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; //沉浸状态栏
        decorView.setSystemUiVisibility(uiOptions);
//        后续补充联网从服务器获取图片,当前版本直使用本地的图片
        Glide.with(this).load(R.drawable.sola).crossFade(1500).into(splash);
        startAppDelay();
        
    }

    private void startAppDelay() {
        splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        },SPLASH_DURATION);
    }

    private void startApp() {
        //到MainActivity中去
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_grow_fade_in_from_bottom,
                android.support.v7.appcompat.R.anim.abc_shrink_fade_out_from_bottom);
        finish();
    }
}
