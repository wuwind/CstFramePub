package com.wuwind.business_1;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.make.makeblelibrary.XxlBle;

/**
 * Created by wuhongfeng
 * data: 2021/4/9
 * desc:
 */
public class BleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if(!XxlBle.runing(getApplicationContext())) {
                    XxlBle.blueStartService(getApplicationContext());
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
