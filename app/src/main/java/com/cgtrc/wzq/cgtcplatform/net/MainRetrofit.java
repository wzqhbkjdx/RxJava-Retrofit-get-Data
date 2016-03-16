package com.cgtrc.wzq.cgtcplatform.net;

import com.cgtrc.wzq.cgtcplatform.inerf.JsonData;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by bym on 16/3/15.
 */
public class MainRetrofit {

    final JsonData jsonDataService;

    final Gson gson = new Gson();

    MainRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(21, TimeUnit.SECONDS);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(MainFactory.HOST)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        jsonDataService = restAdapter.create(JsonData.class);
    }

    public JsonData getService() {
        return jsonDataService;
    }
}
