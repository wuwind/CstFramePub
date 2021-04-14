package com.wuwind.business_1;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.make.makeblelibrary.XxlBle;
import com.wuwind.common.IComponentApplication;

/**
 * Created by wuhongfeng
 * data: 2021/4/9
 * desc:
 */
public class BleApp extends Application implements IComponentApplication {

    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    @Override
    public void init(final Application application) {
        activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (!XxlBle.runing(application.getApplicationContext())) {
                    XxlBle.blueStartService(application.getApplicationContext());
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
        };
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override
    public void deInit(Application application) {
        application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }


}
