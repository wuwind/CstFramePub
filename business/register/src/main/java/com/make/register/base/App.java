package com.make.register.base;

import android.app.Application;

import com.wuwind.common.IComponentApplication;
import com.wuwind.corelibrary.base.BaseApplication;
import com.wuwind.corelibrary.utils.PreferencesUtils;

public class App extends BaseApplication implements IComponentApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    @Override
    public void init(Application application) {
        PreferencesUtils.init(application);
    }
}
