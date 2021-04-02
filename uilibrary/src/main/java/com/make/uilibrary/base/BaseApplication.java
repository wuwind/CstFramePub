package com.make.uilibrary.base;

import android.app.Application;
import android.content.Context;



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
    }

}