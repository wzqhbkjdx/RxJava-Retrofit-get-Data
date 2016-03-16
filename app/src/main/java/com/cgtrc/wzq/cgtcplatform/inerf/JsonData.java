package com.cgtrc.wzq.cgtcplatform.inerf;

import com.cgtrc.wzq.cgtcplatform.model.RSSItem;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by bym on 16/3/15.
 */
public interface JsonData {

    @GET("/JsonRss/TestServlet")
    Observable<List<RSSItem>> getJsonData();
}
