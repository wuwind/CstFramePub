package com.wuwind.common;

import android.app.Application;

import com.common.BuildConfig;


/**
 * Created by wuhf on 2020/6/17.
 * Description ：
 */
public class MyApplication extends Application implements IComponentApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    private void modulesApplicationInit() {
        String[] MODULESLIST = BuildConfig.applications;
        for (String moduleImpl : MODULESLIST) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication) {
                    ((IComponentApplication) obj).init(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(Application application) {
        //Module类的APP初始化
        modulesApplicationInit();
    }
}