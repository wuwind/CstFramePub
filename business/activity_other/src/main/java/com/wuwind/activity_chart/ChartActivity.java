package com.wuwind.activity_chart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.ZRoute;

@ZRoute(RouterPathConst.PATH_ACTIVITY_OTHER)
public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent intent = new Intent();
        intent.putExtra("name","wwwerewrwe");
        setResult(RESULT_OK, intent);
        finish();
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = getIntent();
//                intent.putExtra("name","wwwerewrwe");
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
    }

}
