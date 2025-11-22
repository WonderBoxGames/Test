package com.wonderboxgames.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activityThis = this;

        TextView textView = findViewById(R.id.info);
        findViewById(R.id.btn_test_1)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn1");
                                textView.setText("btn1");
                            }
                        }
                );

        findViewById(R.id.btn_test_2)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn2");
                                textView.setText("btn2");
                            }
                        }
                );

        findViewById(R.id.btn_test_3)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn3");
                                textView.setText("btn3");
                            }
                        }
                );

        findViewById(R.id.btn_test_4)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn4");
                                textView.setText("btn4");
                            }
                        }
                );
    }
}
