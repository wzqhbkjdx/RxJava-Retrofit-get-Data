package com.cgtrc.wzq.cgtcplatform.ui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseActivity;
import com.cgtrc.wzq.cgtcplatform.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/4/7.
 */
public class NewsOriginalActivity extends BaseActivity {

    @Bind(R.id.frame_container)
    FrameLayout frame_container;
    @Bind(R.id.progress)
    ProgressBar progress;

    private WebView webView;

    private String newsOriginalLink;

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolbar();
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        newsOriginalLink = intent.getStringExtra(Constants.LINK);
        initWebView();
    }

    @Override
    protected void initToolbar() {
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected int getLayoutId() {
        layoutId = R.layout.activity_news_original;
        return layoutId;
    }

    private void initWebView() {
        webView = new WebView(this);
        frame_container.addView(webView);
        webView.setVisibility(View.INVISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.loadUrl(newsOriginalLink);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(final WebView view, int newProgress) {
                super.onProgressChanged(view,newProgress);
                if (newProgress == 100) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                            progress.setVisibility(View.GONE);
                        }
                    }, 300);
                }
            }
        });
    }



}
