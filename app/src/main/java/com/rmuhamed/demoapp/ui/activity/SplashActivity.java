package com.rmuhamed.demoapp.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rmuhamed.demoapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);

        this.runSplash();
    }

    private void runSplash() {
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                launchNextActivity();
            }
        }.start();
    }

    private void launchNextActivity() {
        Intent nextActivityIntent = new Intent(this, ItemListActivity.class);

        this.startActivity(nextActivityIntent);
        this.finish();
    }
}
