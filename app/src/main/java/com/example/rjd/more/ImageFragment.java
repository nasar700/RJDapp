package com.example.rjd.more;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rjd.FullScreenImage;
import com.example.rjd.data.ClickListener;
import com.example.rjd.data.Data;
import com.example.rjd.R;
import com.example.rjd.data.Item;

import java.util.ArrayList;

public class ImageFragment extends Fragment implements ClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_image,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        ArrayList<Data> dataList = new ArrayList<>();

        Data data1 = new Data();
        data1.setImgId("");
        data1.setTitle("Title1");
        data1.setDesciption("Desc1");
        dataList.add(data1);
        Data data2 = new Data();
        data2.setImgId("");
        data2.setTitle("Title2");
        data2.setDesciption("Desc2");
        dataList.add(data2);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ImageAdapter adapter = new ImageAdapter(dataList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Item data) {
       // Log.d("Data: ",data.getTitle());
        Intent intent = new Intent(getActivity(), FullScreenImage.class);
        startActivity(intent);
    }
}
