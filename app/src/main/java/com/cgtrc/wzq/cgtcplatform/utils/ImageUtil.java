package com.cgtrc.wzq.cgtcplatform.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cgtrc.wzq.cgtcplatform.App;

/**
 * Created by bym on 16/3/18.
 */
public class ImageUtil {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }

    public static void load( String url, int animationId, ImageView view) {
        Glide.with(App.context)
                .load(url)
                .animate(animationId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}
