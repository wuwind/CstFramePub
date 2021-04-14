package com.wuwind.common;

import android.app.Application;

import com.common.BuildConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wuhf on 2020/6/17.
 * Description ：
 */
public class MyApplication extends Application implements IComponentApplication {

    private List<IComponentApplication> applications;
    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    private void modulesApplicationInit() {
        applications = new ArrayList<>();
        String[] MODULESLIST = BuildConfig.applications;
        for (String moduleImpl : MODULESLIST) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication) {
                    ((IComponentApplication) obj).init(this);
                    applications.add((IComponentApplication) obj);
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

    @Override
    public void deInit(Application application) {
        if(null != applications) {
            for (IComponentApplication iComponentApplication : applications) {
                iComponentApplication.deInit(application);
            }
        }
    }


}