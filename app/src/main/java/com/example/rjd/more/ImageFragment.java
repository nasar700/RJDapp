package com.example.rjd.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rjd.FullScreenImage;
import com.example.rjd.data.Constants;
import com.example.rjd.data.ImageClickListener;
import com.example.rjd.data.VideoClickListener;
import com.example.rjd.data.Data;
import com.example.rjd.R;
import com.example.rjd.data.Item;
import com.example.rjd.data.YoutubeData;
import com.example.rjd.network.APIClient;
import com.example.rjd.network.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageFragment extends Fragment implements ImageClickListener {

    private APIInterface apiInterface;
    private ImageAdapter adapter;
    private ProgressBar progress;
    private ArrayList<String> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_image,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        fetchImageData();
    }

    private void init(View view){

        ArrayList<String> dataList = new ArrayList<>();
        progress = view.findViewById(R.id.progress_circular);

//        for (int i = 0;i<20;i++){
//            Data data1 = new Data();
//            data1.setImgId("");
//            data1.setTitle("Title"+i);
//            dataList.add(data1);
//        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new ImageAdapter(dataList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(String data) {
       // Log.d("Data: ",data.getTitle());
        Intent intent = new Intent(getActivity(), FullScreenImage.class);
        intent.putExtra(Constants.imageId,data);
        intent.putExtra(Constants.imageList,imageList);
        startActivity(intent);
    }

    void fetchImageData(){
        progress.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClientMock().create(APIInterface.class);

        Call<ArrayList<String>> call = apiInterface.fetchImageList();

        call.enqueue(new Callback<ArrayList<String>>() {

            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                progress.setVisibility(View.GONE);
//                Log.d("===Response", response.body().getNextPageToken());
//                youtubeData = response.body();
                imageList  = response.body();
                adapter.updateData(imageList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                call.cancel();
                progress.setVisibility(View.GONE);
            }
        });
    }

}
