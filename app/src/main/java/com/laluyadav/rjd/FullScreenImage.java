package com.laluyadav.rjd;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laluyadav.rjd.data.Constants;
import com.laluyadav.rjd.data.ImageClickListener;
import com.laluyadav.rjd.image.ImageDetailAdapter;
import com.laluyadav.rjd.utils.AdsManagerUtil;

import java.util.ArrayList;

public class FullScreenImage extends AppCompatActivity implements ImageClickListener {

    private String imageUrl;
    private ImageView imageView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_activity);
        AdsManagerUtil.showInterstitialAd();
        showBanner();
        initView();
       showData();
    }

    private void showBanner(){
        LinearLayout adsMobBanner = findViewById(R.id.mainLayout);
        AdsManagerUtil.showAdMObBanner(this, adsMobBanner);
    }

    private void showData(){
        if(getIntent().getExtras() != null){
            imageUrl = getIntent().getExtras().getString(Constants.imageId);
            ArrayList<String> dataList = getIntent().getStringArrayListExtra(Constants.imageList);

            onClick(imageUrl);

            ImageDetailAdapter adapter = new ImageDetailAdapter(dataList,this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setAdapter(adapter);
        }
    }



    private void initView(){
        imageView = findViewById(R.id.image_fullscreen);
        recyclerView =findViewById(R.id.recycler_view);
    }

    @Override
    public void onClick(String url) {
        Glide.with(this)
                .load(url)
                .into(imageView);
    }
}

