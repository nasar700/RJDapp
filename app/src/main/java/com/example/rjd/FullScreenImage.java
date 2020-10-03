package com.example.rjd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rjd.data.Constants;
import com.example.rjd.data.Data;
import com.example.rjd.data.ImageClickListener;
import com.example.rjd.data.YoutubeData;
import com.example.rjd.home.HomeAdapter;
import com.example.rjd.image.ImageDetailAdapter;
import com.example.rjd.more.ImageAdapter;

import java.util.ArrayList;

public class FullScreenImage extends AppCompatActivity implements ImageClickListener {

    private String imageUrl;
    private ImageView imageView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_activity);
        initView();
       showData();
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

