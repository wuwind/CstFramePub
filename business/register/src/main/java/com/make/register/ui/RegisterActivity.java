package com.make.register.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.make.utilcode.util.SPUtils;
import com.make.utilcode.util.ToastUtils;
import com.wuwind.common.RouterPathConst;
import com.wuwind.cstframe.register.R;
import com.wuwind.zrouter_annotation.ZRoute;

@ZRoute(RouterPathConst.PATH_ACTIVITY_REGISTER)
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SPUtils.getInstance().put("register", "user123");
        ToastUtils.showShort(SPUtils.getInstance().getString("register"));
    }
}