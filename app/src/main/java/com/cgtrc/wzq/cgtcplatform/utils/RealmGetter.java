package com.cgtrc.wzq.cgtcplatform.utils;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by bym on 16/3/18.
 */
public class RealmGetter {

    public static Realm getRealm(RealmConfiguration realmConfiguration) {
        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex){
                throw ex;
                //No Realm file to remove.
            }
        }

    }
}
