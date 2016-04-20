package com.cgtrc.wzq.cgtcplatform.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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

    public static void load(Context context, String url, final ImageView view,  boolean isComplete) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(view.getWidth(), view.getHeight()) {
                    @Override
                    public void onResourceReady(Bitmap bmp, GlideAnimation anim) {
                        view.setImageBitmap(bmp);

                    }
                });

    }
}
