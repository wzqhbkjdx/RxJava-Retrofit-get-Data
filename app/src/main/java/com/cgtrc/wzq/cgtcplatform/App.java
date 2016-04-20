package com.cgtrc.wzq.cgtcplatform;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by bym on 16/3/15.
 * TODO: 1. 内存泄露监测
 *       2. logger打印log的使用
 */
public class App extends Application {
//    预留的位置内存泄露监测 private RefWatcher refWatcher;
    public static Context context;
    public static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher = LeakCanary.install(this);
        context = this;
//        Logger.init();
        setupRealm();//配置Realm数据库

//        预留位置使用logger只有调试模式下 才启用日志输出
//        if(BuildConfig.DEBUG){
//            Logger.init("Gank").hideThreadInfo().setMethodCount(0);
//        }else{
//            Logger.init("Gank").setLogLevel(LogLevel.NONE);
//        }
    }

    public static Context getContext(){
        return context;
    }

    /**
     * 配置数据库
     */
    private void setupRealm() {
        realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

//    public static RefWatcher getWatcher(Context context) {
//        ProjectApp application = (ProjectApp) context.getApplicationContext();
//        return application.refWatcher;
//    }
}
