package com.example.rjd.more;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rjd.data.VideoClickListener;
import com.example.rjd.data.Constants;
import com.example.rjd.R;
import com.example.rjd.data.Item;
import com.example.rjd.data.YoutubeData;
import com.example.rjd.network.APIClient;
import com.example.rjd.network.APIInterface;
import com.example.rjd.youtube.VideoDetailActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragment extends Fragment implements VideoClickListener {

    private APIInterface apiInterface;
    private VideoAdapter adapter;
    private ProgressBar progress;
    private YoutubeData youtubeData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_video,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        fetchVideoData();
    }

    private void init(View view){
        progress = view.findViewById(R.id.progress_circular);
        ArrayList<Item> dataList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new VideoAdapter(dataList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    void fetchVideoData(){
        progress.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<YoutubeData> call = apiInterface.fetchVideoList(Constants.part,
                Constants.channelId, Constants.maxResults_50,
                Constants.key, Constants.fields, Constants.order);

        call.enqueue(new Callback<YoutubeData>() {

            @Override
            public void onResponse(Call<YoutubeData> call, Response<YoutubeData> response) {
                progress.setVisibility(View.GONE);
                Log.d("===Response", response.body().getNextPageToken());
                youtubeData = response.body();
                adapter.updateData(youtubeData.getItems());
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<YoutubeData> call, Throwable t) {
                call.cancel();
                progress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(Item data) {
        Log.d("Data: ",data.getSnippet().getTitle());
        Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
        intent.putExtra(Constants.videoId,data.getId().getVideoId());
        intent.putExtra(Constants.videoList,youtubeData);
        startActivity(intent);
    }
}
