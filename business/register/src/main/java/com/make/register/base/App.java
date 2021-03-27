package com.make.register.base;

import android.app.Application;

import com.wuwind.common.IComponentApplication;

public class App extends Application implements IComponentApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    @Override
    public void init(Application application) {
    }
}
