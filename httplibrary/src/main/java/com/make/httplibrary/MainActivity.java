package com.make.httplibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.make.httplibrary.net.request.LoginRequest;
import com.make.httplibrary.net.response.LoginResponse;
import com.make.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        new LoginRequest(false).requset();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLoginResult(LoginResponse response) {
        ToastUtils.showShort(response.msg);
    }
}