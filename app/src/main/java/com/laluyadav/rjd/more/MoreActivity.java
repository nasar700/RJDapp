package com.laluyadav.rjd.more;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.laluyadav.rjd.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laluyadav.rjd.utils.AdsManagerUtil;

public class MoreActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        showBanner();
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new VideoFragment());
    }

    private void showBanner(){
        LinearLayout adsMobBanner = findViewById(R.id.mainLayout);
        AdsManagerUtil.showAdMObBanner(this, adsMobBanner);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_video:
                fragment = new VideoFragment();
                return loadFragment(fragment);

            case R.id.navigation_image:
                fragment = new ImageFragment();
                return loadFragment(fragment);

            case R.id.navigation_share:
                onShare();
                return true;

                default:
                return true;
        }
    }

    private void onShare(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT , "https://play.google.com/store/apps/details?id="+"com.example.rjd");
        intent.setType("text/plain");

        try {
           startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this , "$appName doesn't installed." , Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this , "$appName doesn't installed." , Toast.LENGTH_SHORT).show();
        }
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
