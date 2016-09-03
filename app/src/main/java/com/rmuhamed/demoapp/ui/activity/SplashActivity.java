package com.rmuhamed.demoapp.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_picture)
    ImageView splashPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        ImageLoader imLoader = new ImageLoader(this);
        imLoader.loadFromResource(R.drawable.splash, this.splashPicture);

        this.runSplash();
    }

    private void runSplash() {
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() { SplashActivity.this.launchNextActivity();}
        }.start();
    }

    private void launchNextActivity() {
        Intent nextActivityIntent = new Intent(this, ItemListActivity.class);

        this.startActivity(nextActivityIntent);
        this.finish();
    }
}
