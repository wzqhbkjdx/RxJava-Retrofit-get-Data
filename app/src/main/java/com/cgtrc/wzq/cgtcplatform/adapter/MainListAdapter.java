package com.cgtrc.wzq.cgtcplatform.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.model.NewsItem;
import com.cgtrc.wzq.cgtcplatform.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bym on 16/3/16.
 */
public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    /**
     * footer是"加载更多"的提示
     */
    private static final int TYPE_FOOTER = 2;

    private final Context context;
    private static IClickMainItemListener mIClickMainItemListener;
    private static ColorFilter mColorFilter;//图片的色彩滤镜
    private boolean hasFooter;
    public static int textGrey;
    public static int textDark;

    private List<NewsItem> newsItems;
    private NewsData newsData;

    public MainListAdapter(Context context) {
        this.context = context;

        /**
         * TODO:List数据为空的时候怎么处理,数据什么时候保存
         */
//        newsItems = DB.findAll(NewsItem.class); //数据库如果没有存储数据,直接查找会导致错误
        newsItems = getDefaultData(); //初始化数据
        /**
         * 初始化滤镜
         * TODO:移动到Banner
         */
        float[] array = new float[] {
                1,0,0,0,-70,
                0,1,0,0,-70,
                0,0,1,0,-70,
                0,0,0,1,0,
        };
        mColorFilter = new ColorMatrixColorFilter(new ColorMatrix(array));
    }

    public void setIClickItemListener(IClickMainItemListener iClickMainItemListener) {
        this.mIClickMainItemListener = iClickMainItemListener;
    }

    public boolean isHasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
        notifyDataSetChanged();
    }

    public void addNews(NewsData newsData) {
        this.newsData = newsData;
        newsItems = newsData.getNewsItems();
        if(newsItems.size() != 0) {
            setHasFooter(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

      if(viewType == TYPE_FOOTER) {
            View view = layoutInflater.inflate(R.layout.footer_loading, parent, false);
            return new FooterViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.fragment_news_item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        textGrey = ContextCompat.getColor(context,R.color.darker_gray);
        textDark = ContextCompat.getColor(context, android.support.design.R.color.abc_primary_text_material_light);

        if(holder instanceof ViewHolder) {
            final ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.newsItem = newsItems.get(position);

            viewHolder.mTitle.setText(viewHolder.newsItem.getTitle());
            viewHolder.mPubDate.setText(viewHolder.newsItem.getPubDate());
            viewHolder.mOriginal.setText(viewHolder.newsItem.getOriginal());

            if(viewHolder.newsItem.getPicLink() != null) {
                //用gilde开源库异步加载图片
                ImageUtil.load(context,viewHolder.newsItem.getPicLink(),viewHolder.mImage);
            }
        }
        return;
    }

    @Override
    public int getItemCount() {

        if (hasFooter) {
            return newsItems.size() + 1;
        }
        return newsItems.size() ;

    }

    @Override
    public int getItemViewType(int position) {

        if(hasFooter && position == newsItems.size() + 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImage;
        public final TextView mTitle;
        public final TextView mPubDate;
        public final TextView mOriginal;
        public final View mItem;
        public NewsItem newsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            newsItem = new NewsItem();
            mImage = (ImageView) itemView.findViewById(R.id.news_img);
            mTitle = (TextView) itemView.findViewById(R.id.news_title);
            mPubDate = (TextView) itemView.findViewById(R.id.news_pubDate);
            mOriginal = (TextView) itemView.findViewById(R.id.news_original);
            mItem = itemView.findViewById(R.id.item_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }

    /**
     * adapter对外开放的接口,设置item的点击事件
     * 后续再补充完善
     */
    public interface IClickMainItemListener {

        void onItemClick(RecyclerView.ViewHolder viewHolder);

    }

    private List<NewsItem> getDefaultData() {
        List<NewsItem> list = new ArrayList<>();
        NewsItem item = new NewsItem();
        item.setDescription("两机专项即将在年内成立");
        item.setLink("www.baidu.com");
        item.setTitle("两机专项");
        item.setOriginal("网易新闻");
        item.setPicLink("");
        item.setPubDate("2016-03-23");
        item.setType("行业动态");
        for(int i = 0; i < 10; i++) {
            list.add(item);
        }
        return list;
    }


}
