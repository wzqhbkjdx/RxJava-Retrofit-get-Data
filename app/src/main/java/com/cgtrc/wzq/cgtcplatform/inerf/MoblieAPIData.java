package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.model.NewsData;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by bym on 16/3/15.
 * TODO:这里的接口方法后续随服务器调整再继续调整
 */
public interface MoblieAPIData {

    @GET("/JsonRss/GetItem")
    Observable<NewsData> getLatestData(@QueryMap Map<String, String> options);


    @GET("/JsonRss/GetItemBefore") //后续服务器实现该功能后再添加
    Observable<NewsData> getBeforeData(@QueryMap Map<String, String> options);

    @GET("/JsonRss/GetNewsDetail")
    Observable<Map<String,String>> getNewsDetail(@Query("detailNo") String number);



}
