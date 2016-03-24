package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.model.NewsData;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by bym on 16/3/15.
 * TODO:这里的接口方法后续随服务器调整再继续调整
 */
public interface MoblieAPIData {

    @GET("/JsonRss/TestServlet")
    Observable<NewsData> getLatestData();


    @GET("/JsonRss/BeforeServlet")
    Observable<NewsData> getBeforeData();

}
