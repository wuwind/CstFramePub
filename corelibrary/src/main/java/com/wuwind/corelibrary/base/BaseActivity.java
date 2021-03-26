package com.wuwind.corelibrary.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Activity基类，写activity必须继承
 * Created by Wuhf on 2016/4/1.
 * Description ：
 */
public abstract class BaseActivity extends Activity {

    private ExitReceiver exitReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeOnCreate();
        super.onCreate(savedInstanceState);
        afterOnCreate();
        setContentView(setContentLayoutId());
        registerExitReceiver();
        initData();
        initView();
        initMonitor();
    }

    private void registerExitReceiver() {
        exitReceiver = new ExitReceiver();
        IntentFilter filter = new IntentFilter();
        registerReceiver(exitReceiver, filter);
    }

    protected void beforeOnCreate() {
    }

    protected void afterOnCreate() {
    }

    protected abstract int setContentLayoutId();

    protected abstract void initView();

    protected void initData() {
    }

    protected abstract void initMonitor();

    @Override
    protected void onDestroy() {
        if (null != exitReceiver) {
            unregisterReceiver(exitReceiver);
            exitReceiver = null;
        }
        super.onDestroy();
    }

    class ExitReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

}
