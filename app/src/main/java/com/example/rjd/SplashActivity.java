package com.example.rjd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rjd.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    protected int _splashTime = 3000;

    private Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
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