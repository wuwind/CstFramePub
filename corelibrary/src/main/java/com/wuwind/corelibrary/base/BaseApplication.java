package com.wuwind.corelibrary.base;

import android.app.Application;
import android.content.Context;

import com.wuwind.corelibrary.utils.CrashHandler;
import com.wuwind.corelibrary.utils.PackageUtil;


public abstract class BaseApplication extends Application {

    public static String uid;
    public static Context context;
    public static boolean networkAvailable;
    public static int versionCode;
    public static String baseUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        uid = PackageUtil.getMyUUID(context);
//        networkAvailable = NetUtil.isConnect(context);
        versionCode = PackageUtil.getVersionCode(this);
    }

    protected void initCrashHandler() {
        new CrashHandler().init();
    }
}