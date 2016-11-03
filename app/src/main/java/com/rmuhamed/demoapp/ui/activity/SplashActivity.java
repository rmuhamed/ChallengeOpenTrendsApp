package com.rmuhamed.demoapp.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.utils.ImageLoader;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {
    public static final int SPLASH_DURATION = 3000;
    public static final int TICK_INTERVAL = 1000;

    private int remainingTime = 5;

    @BindView(R.id.splash_picture)
    ImageView splashPicture;

    @BindView(R.id.remaining_time_label)
    TextView remainingTimeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);

        //Fabric initialization
        Fabric.with(this, new Crashlytics());
        //ButterKnife initialization
        ButterKnife.bind(this);

        this.loadImageForSplash();
        this.performSplash(SPLASH_DURATION);
    }

    private void loadImageForSplash() {
        ImageLoader.getInstance().with(this).loadFromResource(R.drawable.splash, this.splashPicture);
    }

    private void performSplash(int splashDuration) {
        new CountDownTimer(splashDuration, TICK_INTERVAL) {
            @Override
            public void onTick(long l) {
                SplashActivity.this.remainingTimeLabel.setText(String.format(Locale.getDefault(), "%d sec", --remainingTime));
            }

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
