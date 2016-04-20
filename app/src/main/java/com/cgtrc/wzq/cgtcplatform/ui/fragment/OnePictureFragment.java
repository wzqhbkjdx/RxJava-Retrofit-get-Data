package com.cgtrc.wzq.cgtcplatform.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.base.BaseFragment;
import com.cgtrc.wzq.cgtcplatform.ui.activity.BannerDetailActivity;
import com.cgtrc.wzq.cgtcplatform.utils.ImageUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by bym on 16/4/10.
 */
public class OnePictureFragment extends BaseFragment {

    @Bind(R.id.photoView)
    PhotoView photoView;

    private Bitmap loadedBitmap;
    String suffix;

    public static Fragment newInstance(String imageUrl) {
        OnePictureFragment fragment = new OnePictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected int getLayoutId() {
        layoutId = R.layout.picture_detail_item;
        return layoutId;
    }

    @Override
    protected void initViews() {
        String url = getArguments().getString("url");
        int last = url.lastIndexOf(".");
        suffix = url.substring(last+1);
        //先检查网络
        if(checkNetworkState()) {
            if(url.startsWith("http://")){
                ImageUtil.load(getContext(),url,photoView);

            }
            ((BannerDetailActivity)getActivity()).hideProgress();
        } else {
            Toast.makeText(getActivity(),"网络不给力,请稍后再试",Toast.LENGTH_SHORT).show();
            ((BannerDetailActivity)getActivity()).hideProgress();
        }


    }

    public void savePic() {
        Toast.makeText(getActivity(),"保存图片",Toast.LENGTH_SHORT).show();

        loadedBitmap = ((GlideBitmapDrawable)photoView.getDrawable()).getBitmap();
        if(loadedBitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if("jpg".equals(suffix)||"jpeg".equals(suffix)){
                loadedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }else{
                loadedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            }
            byte[] byteArray = stream.toByteArray();
            File dir=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/CGTPIC" );
            if(!dir.isFile()){
                dir.mkdir();
            }
            String fileName = UUID.randomUUID() +"."+suffix;
            File file=new File(dir, fileName);
            try {
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(byteArray, 0 , byteArray.length);
                fos.flush();
                fos.close();
                Toast.makeText(getActivity(),"保存成功:/CGTPIC/"+fileName,Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            Toast.makeText(getActivity(),"图片正在加载....",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }
}
