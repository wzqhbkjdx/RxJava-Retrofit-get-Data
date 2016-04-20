package com.cgtrc.wzq.cgtcplatform.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.bean.CircleData;
import com.cgtrc.wzq.cgtcplatform.bean.RealmCircleItem;
import com.cgtrc.wzq.cgtcplatform.bean.RealmComment;
import com.cgtrc.wzq.cgtcplatform.bean.User;
import com.cgtrc.wzq.cgtcplatform.utils.ImageUtil;
import com.cgtrc.wzq.cgtcplatform.widgets.CircularImage;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by bym on 16/4/12.
 */
public class CircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;

    private final Context mContext;
    public static int textGrey;

    private List<RealmCircleItem> circleItems;
    private CircleData data;

    private IClickCircleItemListener mListener;

    public CircleAdapter(Context context) {
        this.mContext = context;

        /**
         * TODO:从数据库获取数据
         */
        //模拟从数据库中获取数据

        circleItems = getDefaultItems();

    }

    public void setListener(IClickCircleItemListener listener) {
        mListener = listener;
    }

    public void addCircleItems(CircleData data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_ITEM) {
            View view = layoutInflater.inflate(R.layout.item_circile_card, parent, false);
            return new CircleItemViewHolder(view,mListener);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Context context = holder.itemView.getContext();
        textGrey = ContextCompat.getColor(context,R.color.darker_gray);

        if(holder instanceof CircleItemViewHolder) {

            final CircleItemViewHolder viewHolder = (CircleItemViewHolder) holder;
            viewHolder.setCircleItem(circleItems.get(position));

            if(viewHolder.circleItem.getHeadPortraitLink() != null) {  //加载User头像图片
                String url = viewHolder.circleItem.getHeadPortraitLink();
                ImageUtil.load(mContext,url,viewHolder.image);
            }

            if(viewHolder.circleItem.getPicLinks() != null) {
                /**
                 * TODO:加载用户上传的图片
                 */
            } else {
                viewHolder.imageView1.setVisibility(View.GONE);
                viewHolder.imageView2.setVisibility(View.GONE);
                viewHolder.imageView3.setVisibility(View.GONE);
            }

            viewHolder.userName.setText(viewHolder.circleItem.getUserName());
            viewHolder.category.setText(viewHolder.circleItem.getCategory());
            viewHolder.pubDate.setText(String.valueOf(viewHolder.circleItem.getPubDate()));
            viewHolder.title.setText(viewHolder.circleItem.getTitle());
            viewHolder.description.setText(viewHolder.circleItem.getDescription());
            viewHolder.priceCount.setText(String.valueOf(viewHolder.circleItem.getPriceCount()));
            viewHolder.commentCount.setText(String.valueOf(viewHolder.circleItem.getCommentList().size()));



        }


    }

    @Override
    public int getItemCount() {
        return circleItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public class CircleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final CardView circleCard; //根布局,用于触发item的点击事件
        public final CircularImage image; //
        public final TextView userName;   //
        public final TextView category;   //
        public final TextView pubDate;    //
        public final TextView title;      //
        public final TextView description;//
        public final ImageView imageView1;//
        public final ImageView imageView2;//
        public final ImageView imageView3;//
        public final ImageView starBtn;
        public final ImageView priceBtn;
        public final TextView priceCount;
        public final ImageView shareBtn;
        public final ImageView commentBtn;
        public final TextView commentCount;

        public RealmCircleItem circleItem;
        public IClickCircleItemListener mListener;

        public CircleItemViewHolder(View itemView, IClickCircleItemListener listener) {

            super(itemView);
            circleCard = (CardView) itemView.findViewById(R.id.cv_circle_card);
            image = (CircularImage) itemView.findViewById(R.id.headIv);
            userName = (TextView) itemView.findViewById(R.id.user_title);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            pubDate = (TextView) itemView.findViewById(R.id.tv_pubDate);
            title = (TextView) itemView.findViewById(R.id.tv_cicle_title);
            description = (TextView) itemView.findViewById(R.id.tv_circle_description);
            imageView1 = (ImageView) itemView.findViewById(R.id.iv_content1);
            imageView2 = (ImageView) itemView.findViewById(R.id.iv_content2);
            imageView3 = (ImageView) itemView.findViewById(R.id.iv_content3);
            starBtn = (ImageView) itemView.findViewById(R.id.starBtn);
            priceBtn = (ImageView) itemView.findViewById(R.id.priceBtn);
            priceCount = (TextView) itemView.findViewById(R.id.price_count);
            shareBtn = (ImageView) itemView.findViewById(R.id.iv_share);
            commentBtn = (ImageView) itemView.findViewById(R.id.iv_comment);
            commentCount = (TextView) itemView.findViewById(R.id.comment_count);

            circleItem = new RealmCircleItem();
            this.mListener = listener;

            //为各View设置点击事件
            circleCard.setOnClickListener(this); //整个item的点击事件
            image.setOnClickListener(this); //用户头像的点击事件
            starBtn.setOnClickListener(this); //收藏按钮点击事件
            priceBtn.setOnClickListener(this); //赞 按钮点击事件
            shareBtn.setOnClickListener(this); //分享按钮点击事件
            commentBtn.setOnClickListener(this); //评论按钮点击事件

        }

        public void setCircleItem (RealmCircleItem item) {
            this.circleItem = item;
        }


        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.cv_circle_card:
                mListener.onItemClick(this);
                    break;
                case R.id.headIv:
                    mListener.onHeadIvClick(circleItem.getUserID(),getLayoutPosition());
                    break;
                case R.id.starBtn:
                    mListener.onStarClick(User.getInstance().getUserID(),getLayoutPosition());
                    break;
                case R.id.priceBtn:
                    mListener.onPriceClick(User.getInstance().getUserID(),getLayoutPosition());
                    break;
                case R.id.iv_share:
                    mListener.onShareClick(getLayoutPosition());
                    break;
                case R.id.iv_comment:
                    mListener.onCommentClick(User.getInstance().getUserID(),getLayoutPosition());
                    break;
                default:
                    break;

            }
        }
    }



    /**
     * adapter对外开放的接口,设置item的点击事件
     *
     */
    public interface IClickCircleItemListener {

        //整个条目的点击事件,跳转到详情页
        void onItemClick(RecyclerView.ViewHolder viewHolder);
        //头像的点击事件
        void onHeadIvClick(long userID, int position);
        //"赞"的点击事件
        void onPriceClick(long userID,int position);
        //"收藏"的点击事件
        void onStarClick(long userID, int position);
        //"分享"的点击事件
        void onShareClick(int position);
        //"评论"的点击事件
        void onCommentClick(long userID, int position);

    }

    private List<RealmCircleItem> getDefaultItems() {

        List<RealmCircleItem> list = new ArrayList<>();
        RealmList<RealmComment> commentList = new RealmList<>();
        for(int i = 0; i < 10; i++) {
            commentList.add(new RealmComment());
        }

        for(int i = 0; i < 10; i++) {
            RealmCircleItem item = new RealmCircleItem();
            item.setPubDate(Long.valueOf("201604021125"));
            item.setTitle("GE 6B燃机国产自动控制系统仿真模型");
            item.setDescription("主要介绍MS6O01B燃气辘机从最初的PG6431A到现在的PG6581B所作的一些改进，重点分析PG6581B的发展特点。");
            item.setCategory("技术交流");
            item.setCommentList(commentList);
            item.setPriceCount(256);
            item.setUserName("隔壁老王");
            list.add(item);
        }

        return list;
    }

}










