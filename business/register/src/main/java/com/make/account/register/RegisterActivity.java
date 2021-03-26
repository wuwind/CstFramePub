package com.make.account.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wuwind.common.RouterPathConst;
import com.wuwind.cstframe.register.R;
import com.wuwind.zrouter_annotation.ZRoute;

@ZRoute(RouterPathConst.PATH_ACTIVITY_REGISTER)
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}