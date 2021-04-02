package com.make.serial.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.make.serial.R;
import com.make.serial.SerialManager;
import com.make.serial.protocol.OpenHandler;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SerialManager.getInstance().init();

        final EditText etNumber = findViewById(R.id.et_number);
        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenHandler openHandler = new OpenHandler(Integer.parseInt(etNumber.getText().toString()));
                openHandler.handleMessage();
            }
        });
    }

}