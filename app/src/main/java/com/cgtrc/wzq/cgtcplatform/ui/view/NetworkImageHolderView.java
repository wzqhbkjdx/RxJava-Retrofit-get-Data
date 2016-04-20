package com.cgtrc.wzq.cgtcplatform.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.model.RealmPicBanner;
import com.cgtrc.wzq.cgtcplatform.ui.activity.BannerDetailActivity;
import com.cgtrc.wzq.cgtcplatform.utils.Constants;
import com.cgtrc.wzq.cgtcplatform.utils.ImageUtil;

/**
 * Created by bym on 16/4/9.
 */
public class NetworkImageHolderView implements Holder<RealmPicBanner> {
    private View view;

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.card_item_big, null);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, int position, final RealmPicBanner banner) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.story_img);
        TextView textView = (TextView) view.findViewById(R.id.story_title);

        String[] picUrlArray = banner.getPicLinks().split("\n");

        ImageUtil.load(context, picUrlArray[0], imageView);
        textView.setText(banner.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BannerDetailActivity.class);
                intent.putExtra(Constants.TITLE, banner.getTitle());
                intent.putExtra(Constants.DESCRIPTION, banner.getDescription());
                intent.putExtra(Constants.LINKS, banner.getPicLinks());
                intent.putExtra(Constants.LINK, banner.getOriginalLink());
                context.startActivity(intent);
            }
        });
    }
}
