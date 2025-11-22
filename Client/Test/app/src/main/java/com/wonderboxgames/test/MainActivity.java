package com.wonderboxgames.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activityThis = this;

        findViewById(R.id.btn_test_1)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn1");
                            }
                        }
                );

        findViewById(R.id.btn_test_2)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn2");
                            }
                        }
                );

        findViewById(R.id.btn_test_3)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn3");
                            }
                        }
                );

        findViewById(R.id.btn_test_4)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn4");
                            }
                        }
                );
    }
}
