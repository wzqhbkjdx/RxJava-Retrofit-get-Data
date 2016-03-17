package com.cgtrc.wzq.cgtcplatform.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.inerf.INewsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bym on 16/3/16.
 */
public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_BANNER = 0;
    /**
     * header是用于表示日期的标题
     */
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;
    /**
     * footer是"加载更多"的提示
     */
    private static final int TYPE_FOOTER = 3;

    private List<INewsData> mList;
    private final Context context;
    private static IClickMainItemListener mIClickMainItemListener;
    private static ColorFilter mColorFilter;//图片的色彩滤镜
    private boolean showHeader;
    private boolean hasFooter;
    public static int textGrey;
    public static int textDark;

    public MainListAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        //初始化滤镜
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

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean isHasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
        notifyDataSetChanged();
    }

    public void addNewsWithClear(List<INewsData> newsList) {
        mList.clear();
        addNews(newsList);
    }

    public void addNews(List<INewsData> newsList) {
        for(INewsData item : newsList) {
            mList.add(item);
        }
    }

    public void clear() {
        showHeader = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_news_item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        INewsData data = mList.get(position);
        if(data.isHeader()){
            return EItemType.ITEM_TYPE_PIC.ordinal();
        } else {
            return EItemType.ITEM_TYPE_NORMAL.ordinal();
        }
    }

    abstract static class ViewHolderItem extends RecyclerView.ViewHolder {
        public ViewHolderItem(View itemView) {
            super(itemView);
        }
        abstract void bindItem(Context context, INewsData data);
    }

    /**
     * adapter对外开放的接口,设置item的点击事件
     * 后续再补充完善
     */
    public interface IClickMainItemListener {

        void onClickGankItemGirl();

        void onClickGankItemNormal();
    }

    /**
     * 新闻类型 枚举
     */
    private enum EItemType {
        ITEM_TYPE_NORMAL,
        ITEM_TYPE_PIC;
    }
}
