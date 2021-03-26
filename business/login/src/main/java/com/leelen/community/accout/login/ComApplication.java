package com.leelen.community.accout.login;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.wuwind.common.IComponentApplication;

/**
 * Created by wuhf on 2020/6/17.
 * Description ：
 */
public class ComApplication extends Application implements IComponentApplication {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void init(Application application) {
        context = application;
        Log.e("CommunityApplication","ComApplication-------");
    }
}
