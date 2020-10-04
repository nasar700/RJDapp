package com.laluyadav.rjd.more;

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

import com.laluyadav.rjd.FullScreenImage;
import com.laluyadav.rjd.data.Constants;
import com.laluyadav.rjd.data.ImageClickListener;
import com.laluyadav.rjd.R;
import com.laluyadav.rjd.network.APIClient;
import com.laluyadav.rjd.network.APIInterface;
import com.laluyadav.rjd.utils.AdsManagerUtil;

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

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new ImageAdapter(dataList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(String data) {
        AdsManagerUtil.showInterstitialAd();
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
