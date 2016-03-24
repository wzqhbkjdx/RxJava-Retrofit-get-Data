package com.cgtrc.wzq.cgtcplatform.net;

import com.cgtrc.wzq.cgtcplatform.inerf.MoblieAPIData;
import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.model.ProvidersData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bym on 16/3/24.
 */
public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MoblieAPIData apiService;
    private OkHttpClient.Builder builder;

    //私有构造方法,用于构造单例类
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    }

    //用静态内部类的方式实现单例类
    private static class SingletonHolder {
        private static final HttpMethods instance = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.instance;
    }

    /**
     *
     * @param subscriber  由调用者传过来的观察者对象
     */
    public void getNewsData (Subscriber<NewsData> subscriber, String url, int type) {

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(MoblieAPIData.class);
        if(type == API.TYPE_LATEST){
            apiService.getLatestData()
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        } else if(type == API.TYPE_BEFORE) {
            apiService.getBeforeData()
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

    public void getProvidersData(Subscriber<ProvidersData> subscriber){

    }



//    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
//
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getCount() == 0) {
//                throw new ApiException(100);
//            }
//            return httpResult.getSubjects();
//        }
//    }
}
