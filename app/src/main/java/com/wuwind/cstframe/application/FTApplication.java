package com.wuwind.cstframe.application;

import com.wuwind.common.MyApplication;
import com.wuwind.zrouter_api.ZRouter;


public class FTApplication extends MyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ZRouter.getInstance().init(this);
//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog();     // 打印日志
//            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this); // 尽可能早，推荐在Application中初始化
//        PreferencesUtils.init(this);
    }

    private boolean isDebug() {
        return true;
    }
}
