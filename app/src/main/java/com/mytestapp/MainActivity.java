package com.mytestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mytestapp.weight.SMSTestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{
        @Bind(R.id.main_sms)
        RelativeLayout main_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("常用控件&功能");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        main_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SMSTestActivity.class);
                startActivity(i);
            }
        });

    }
}
