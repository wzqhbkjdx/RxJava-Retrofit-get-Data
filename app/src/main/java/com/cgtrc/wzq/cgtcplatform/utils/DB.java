package com.cgtrc.wzq.cgtcplatform.utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by bym on 16/3/17.
 */
public class DB {

    public static Realm realm = Realm.getDefaultInstance();

    public static void saveOrUpdate(RealmObject realmObject) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObject);
        realm.commitTransaction();
    }

    public static <T extends RealmObject> void saveList(List<T> realmObjects) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObjects);
        realm.commitTransaction();
    }

    public static void save(RealmObject realmObject) {
        realm.beginTransaction();
        realm.copyToRealm(realmObject);
        realm.commitTransaction();
    }

    public static <T extends RealmObject> T getById(int id, Class<T> realmObjectClass) {
        return realm.where(realmObjectClass).equalTo("id", id).findFirst();
    }

    public static <T extends RealmObject> RealmResults<T> findAll(Class<T> realmObjectClass) {
        return realm.where(realmObjectClass).findAll();
    }

    public static <T extends RealmObject> RealmResults<T> findAllDateSorted(Class<T> realmObjectClass) {
        RealmResults<T> results = findAll(realmObjectClass);
        results.sort(Constants.DATE, Sort.DESCENDING);
        return results;
    }

    public static <T extends RealmObject> void clear(Class<T> realmObjectClass) {
        realm.beginTransaction();
        findAll(realmObjectClass).clear();
        realm.commitTransaction();
    }

    /**
     * TODO:加载新闻图片的时候会用到
     */
//图片新闻的时候会用到
//    public static RealmResults<Image> getImages(int type) {
//        RealmResults<Image> results = realm.where(Image.class).equalTo("type", type).findAll();
//        if (type == PictureFragment.TYPE_GANK) {
//            results.sort("publishedAt", Sort.DESCENDING);
//        } else {
//            results.sort("url");
//        }
//        return results;
//    }
}
