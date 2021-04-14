package com.make.uilibrary.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.viewbinding.ViewBinding;

import com.make.uilibrary.click.OnClickUtils;
import com.make.utilcode.util.GenericUtil;

import java.lang.reflect.Method;


/**
 * Activity基类，写activity必须继承
 * Description ：
 */
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    private ExitReceiver exitReceiver;
    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeOnCreate();
        super.onCreate(savedInstanceState);
        afterOnCreate();
        binding = getBinding();
        setContentView(binding.getRoot());
        OnClickUtils.init(this);
        registerExitReceiver();
        init();
    }

    protected T getBinding(){
        try {
            Class clazz = GenericUtil.getSuperClassGenricType(getClass());
            Method method = clazz.getMethod("inflate", LayoutInflater.class);
            return (T) method.invoke(null, getLayoutInflater());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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


    protected abstract void init();

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
