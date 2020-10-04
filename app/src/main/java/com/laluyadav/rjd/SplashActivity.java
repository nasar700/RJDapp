package com.laluyadav.rjd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.laluyadav.rjd.home.HomeActivity;
import com.laluyadav.rjd.utils.AdsManagerUtil;

public class SplashActivity extends AppCompatActivity {
    protected int _splashTime = 3000;

    private Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                AdsManagerUtil.initInterstitialAd(SplashActivity.this);
            }
        });

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this){
                        wait(_splashTime);
                    }

                } catch(InterruptedException e) {}
                finally {
                    finish();

                    Intent i = new Intent();
                    i.setClass(SplashActivity.this, HomeActivity.class);
                    startActivity(i);

                    //stop();
                }
            }
        };

        splashTread.start();

    }

}