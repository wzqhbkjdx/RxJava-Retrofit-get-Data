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

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.model.RealmNewsItem;
import com.cgtrc.wzq.cgtcplatform.model.RealmPicBanner;
import com.cgtrc.wzq.cgtcplatform.ui.view.NetworkImageHolderView;
import com.cgtrc.wzq.cgtcplatform.utils.DB;
import com.cgtrc.wzq.cgtcplatform.utils.ImageUtil;

import java.util.List;

/**
 * Created by bym on 16/3/16.
 */
public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;

    private static final int TYPE_BANNER = 3;

    private final Context context;
    private static IClickMainItemListener mIClickMainItemListener;
    private static ColorFilter mColorFilter;//图片的色彩滤镜

    public static int textGrey;
    public static int textDark;

    private List<RealmNewsItem> newsItems;
    private List<RealmPicBanner> banners;
    private NewsData newsData;


    public MainListAdapter(Context context) {
        this.context = context;

        newsItems = DB.findAllDateSorted("pubDate",RealmNewsItem.class); //初始化数据
        banners = DB.findAllDateSorted("pubDate",RealmPicBanner.class);
//        newsItems = DB.findAll(RealmNewsItem.class);

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


    public void addNews(NewsData newsData) {
        this.newsData = newsData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

      if(viewType == TYPE_BANNER) {
          View view = layoutInflater.inflate(R.layout.fragment_news_banner, parent, false);
          return new BannerViewHolder(view);
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
            viewHolder.newsItem = newsItems.get(position - 1);

            viewHolder.mTitle.setText(viewHolder.newsItem.getTitle());
            viewHolder.mPubDate.setText(String.valueOf(viewHolder.newsItem.getPubDate()));
            viewHolder.mOriginal.setText(viewHolder.newsItem.getOriginal());

            if(viewHolder.newsItem.getPicLink() != null) {
                if(viewHolder.newsItem.getPicLink().startsWith("http://")) {
                    ImageUtil.load(context,viewHolder.newsItem.getPicLink(),viewHolder.mImage);
                    viewHolder.mImage.setVisibility(View.VISIBLE);
                }
            } else {
                viewHolder.mImage.setVisibility(View.GONE);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIClickMainItemListener.onItemClick(viewHolder);
                }
            });
        } else if(holder instanceof BannerViewHolder) {
            final BannerViewHolder itemHolder = (BannerViewHolder) holder;
            itemHolder.banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, banners);
        }

    }

    @Override
    public int getItemCount() {

        return newsItems.size() + 1; //有个Banner

    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0) {
            return TYPE_BANNER;
        }

        return TYPE_ITEM;
    }



    public class BannerViewHolder extends RecyclerView.ViewHolder {
        public final ConvenientBanner<RealmPicBanner> banner;

        public BannerViewHolder(View view) {
            super(view);
            banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImage;
        public final TextView mTitle;
        public final TextView mPubDate;
        public final TextView mOriginal;
        public final View mItem;
        public RealmNewsItem newsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            newsItem = new RealmNewsItem();
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
     *
     */
    public interface IClickMainItemListener {

        void onItemClick(RecyclerView.ViewHolder viewHolder);

    }




}
