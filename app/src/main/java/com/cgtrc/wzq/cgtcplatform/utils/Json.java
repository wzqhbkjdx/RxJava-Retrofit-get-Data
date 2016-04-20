package com.cgtrc.wzq.cgtcplatform.utils;

import com.cgtrc.wzq.cgtcplatform.model.NewsData;
import com.cgtrc.wzq.cgtcplatform.model.RealmNewsData;
import com.cgtrc.wzq.cgtcplatform.model.RealmNewsItem;
import com.cgtrc.wzq.cgtcplatform.model.RealmPicBanner;
import com.cgtrc.wzq.cgtcplatform.model.RealmString;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.lang.reflect.Type;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.internal.IOException;

/**
 * Created by bym on 16/4/5.
 */
public class Json {

    public Json() {
    }

    public static Type token = new TypeToken<RealmList<RealmString>>() {
    }.getType();


    public static Gson mGson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaringClass().equals(RealmObject.class);
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    })
            .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {

                @Override
                public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
                    // Ignore
                }

                @Override
                public RealmList<RealmString> read(JsonReader in) throws IOException, java.io.IOException {
                    RealmList<RealmString> list = new RealmList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(new RealmString(in.nextString()));
                    }
                    in.endArray();
                    return list;
                }
            }).create();

    public static NewsData parseNewsData(String latest) {
        return mGson.fromJson(latest, NewsData.class);
    }

    public static RealmNewsItem parseRealmNewsItem(String data) {
        return mGson.fromJson(data, RealmNewsItem.class);
    }

    public static RealmNewsData parseRealmNewsData(String data) {
        return mGson.fromJson(data, RealmNewsData.class);
    }

    public static RealmPicBanner parseRealmPicBanner(String data) {
        return mGson.fromJson(data, RealmPicBanner.class);
    }

}
