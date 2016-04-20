package com.cgtrc.wzq.cgtcplatform.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseActivity;
import com.cgtrc.wzq.cgtcplatform.inerf.IBaseData;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsView;
import com.cgtrc.wzq.cgtcplatform.ui.fragment.OnePictureFragment;
import com.cgtrc.wzq.cgtcplatform.utils.Constants;
import com.cgtrc.wzq.cgtcplatform.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/4/10.
 */
public class BannerDetailActivity extends BaseActivity implements INewsView{
    @Bind(R.id.picture_detail_page)
    protected ViewPager picDetailPager;
    @Bind(R.id.tv_pic_title)
    protected TextView tv_pic_title;
    @Bind(R.id.tv_pic_content)
    protected TextView tv_pic_content;
    @Bind(R.id.tv_pic_count)
    protected TextView tv_pic_count;
    @Bind(R.id.progress)
    protected ProgressBar progress;

    private List<String> picUrlList;
//    private static DisplayImageOptions options;
    private PictureAdapter adapter;
    private int pictureCount;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private String title;
    private String link;
    private String links;
    private String description;

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolbar();

        adapter = new PictureAdapter(getSupportFragmentManager(),fragmentList);
        picDetailPager.setAdapter(adapter);
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
        layoutId = R.layout.activity_picbanner_detail;
        return layoutId;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra(Constants.TITLE);
        link = intent.getStringExtra(Constants.LINK);
        links = intent.getStringExtra(Constants.LINKS);
        description = intent.getStringExtra(Constants.DESCRIPTION);

        String[] picLinks = links.split("\n");
        pictureCount = picLinks.length;
        tv_pic_count.setText("1/" + pictureCount);
        tv_pic_title.setText(title);
        tv_pic_content.setText(description);

        addFragments(picLinks);

        picDetailPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_pic_count.setText((position + 1) + "/" + pictureCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addFragments(String[] picLinks) {
        ArrayList<Fragment> pictureFragments = new ArrayList<Fragment>();
        for(int i = 0; i < picLinks.length; i++) {
            pictureFragments.add(OnePictureFragment.newInstance(picLinks[i]));
        }

        adapter.addFragment(pictureFragments);

//        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share,menu);
        return true;
    }

    /**
     * "分享"的实现
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                OnePictureFragment fragment = (OnePictureFragment) adapter.getItem(picDetailPager.getCurrentItem());
                fragment.savePic();
                break;
            case R.id.action_share:
//                Toast.makeText(BannerDetailActivity.this,"分享功能",Toast.LENGTH_SHORT).show();
                ShareUtil.shareText(BannerDetailActivity.this, link);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void addNews(IBaseData news) {

    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    class PictureAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> pictureFragments;

        public PictureAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            pictureFragments = new ArrayList<Fragment>();
            pictureFragments.addAll(list);
        }

        public void addFragment(ArrayList<Fragment> list){
            pictureFragments.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {

            return pictureFragments.get(position);
        }

        @Override
        public int getCount() {
            return pictureFragments.size();
        }
    }
}
