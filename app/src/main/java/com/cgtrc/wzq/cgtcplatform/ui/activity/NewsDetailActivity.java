package com.cgtrc.wzq.cgtcplatform.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseActivity;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetailPresenter;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetailView;
import com.cgtrc.wzq.cgtcplatform.model.NewsDetail;
import com.cgtrc.wzq.cgtcplatform.presenter.NewsDetailPresenter;
import com.cgtrc.wzq.cgtcplatform.utils.ChangePx;
import com.cgtrc.wzq.cgtcplatform.utils.Constants;
import com.cgtrc.wzq.cgtcplatform.utils.ImageUtil;
import com.cgtrc.wzq.cgtcplatform.utils.ShareUtil;
import com.cgtrc.wzq.cgtcplatform.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/4/6.
 */
public class NewsDetailActivity extends BaseActivity implements INewsDetailView {
    @Bind(R.id.detail_img)
    ImageView detailImg;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.frame_container)
    FrameLayout frame_container;
    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    private String newsDetailNumber;
    private String newsTitle;

    private NewsDetail newsDetail;
    private INewsDetailPresenter presenter;
    private String newsDetailLink;
    private String picLink;
    private long detailNumber;

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolbar();

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
    protected int getLayoutId() {
        layoutId = R.layout.activity_news_detail;
        return layoutId;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        newsDetailNumber = intent.getStringExtra(Constants.NUMBER);
        newsTitle = intent.getStringExtra(Constants.TITLE);
        newsDetailLink = intent.getStringExtra(Constants.LINK);
        picLink = intent.getStringExtra(Constants.PICLINK);
        toolbarLayout.setTitle(newsTitle);

        presenter = new NewsDetailPresenter(this,this,newsDetailNumber);
        presenter.loadNewsDetail();
        initFAB();
    }

    private void initFAB() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.shareText(NewsDetailActivity.this, newsDetailLink);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.cancelConnection(); //取消网络连接
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelConnection(); //取消网络连接
    }

    /**
     * INewsDetailView定义的接口方法
     */
    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    /**
     * INewsDetailView定义的接口方法
     */
    @Override
    public void showDetail(NewsDetail detailNews) {
        newsDetail = detailNews;
        String content = detailNews.getNewsDetailContent();
        updateNewsContent(content); //展示新闻详情内容

        hideProgress();
    }

    /**
     * INewsDetailView定义的接口方法
     */
    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    /**
     * INewsDetailView定义的接口方法
     */
    @Override
    public void showLoadFailed(String msg) {
        UiUtils.showSnackByTag(frame_container,msg);
    }

    private void updateNewsContent(String content) {


        /**
         * TODO:设置题图的图片
         */
        if(picLink != null && picLink.startsWith("http://")) {
            ImageUtil.load(this,picLink,detailImg);
        }

        int topMargin = ChangePx.dip2px(this, 10);
        LinearLayout.LayoutParams layoutParamsTextMain = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsTextMain.topMargin = topMargin;
        layoutParamsTextMain.bottomMargin = topMargin;
        layoutParamsTextMain.leftMargin = topMargin;
        layoutParamsTextMain.rightMargin = topMargin;
        layoutParamsTextMain.gravity = Gravity.LEFT;

        String[] paragraph = content.split("\n");
        for(String str : paragraph) {
            if(str.startsWith("http://")) {
                /**
                 * 加载图片
                 */
                ImageView iv = new ImageView(this);
                ImageUtil.load(this,str,iv);
                ll_content.addView(iv,layoutParamsTextMain);
            } else {
                /**
                 * 处理文字
                 */
                int textSize = ChangePx.dip2px(this, 6);
                TextView tv = new TextView(this);
                tv.setTextSize(textSize);
                tv.setTextColor(Color.parseColor("#99202020"));
                tv.setText(str);
//                if(str.startsWith("核心提示")) {
//                    tv.setBackgroundColor(Color.parseColor("#6F71715"));
//                }
                ll_content.addView(tv, layoutParamsTextMain);
            }
        }

        //阅读原文
        int textSize = ChangePx.dip2px(this, 6);
        TextView tv = new TextView(this);
        tv.setTextSize(textSize);

        tv.setTextColor(Color.parseColor("#DC1915"));

        //创建一个 SpannableString对象

        Spannable underlineSpan = new SpannableString("点击这里阅读原文");
        UnderlineSpan ulSpan = new UnderlineSpan();  //设置下划线样式
        underlineSpan.setSpan(ulSpan, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(underlineSpan);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewsDetailActivity.this,"阅读原文",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewsDetailActivity.this, NewsOriginalActivity.class);
                intent.putExtra(Constants.LINK,newsDetailLink);
                startActivity(intent);
            }
        });
        ll_content.addView(tv,layoutParamsTextMain);
    }
}
