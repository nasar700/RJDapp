package com.laluyadav.rjd.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laluyadav.rjd.R;
import com.laluyadav.rjd.data.VideoClickListener;
import com.laluyadav.rjd.data.Constants;
import com.laluyadav.rjd.data.Item;
import com.laluyadav.rjd.data.YoutubeData;
import com.laluyadav.rjd.more.MoreActivity;
import com.laluyadav.rjd.network.APIClient;
import com.laluyadav.rjd.network.APIInterface;
import com.laluyadav.rjd.utils.AdsManagerUtil;
import com.laluyadav.rjd.youtube.VideoDetailActivity;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements VideoClickListener {

    private APIInterface apiInterface;
    private Button button;
    private HomeAdapter adapter;
    private ProgressBar progress;
    private YoutubeData youtubeData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AdsManagerUtil.showInterstitialAd();
        showBanner();
        initView();
        fetchVideoData();
    }

    private void showBanner(){
        LinearLayout adsMobBanner = findViewById(R.id.mainLayout);
        AdsManagerUtil.showAdMObBanner(this, adsMobBanner);
    }

    private void initView(){
        progress = findViewById(R.id.progress_circular);

        ArrayList<Item> dataList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new HomeAdapter(dataList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        button= findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, MoreActivity.class);
        startActivity(intent);
    }

    void fetchVideoData(){
        progress.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClientMock().create(APIInterface.class);

        Call<YoutubeData> call = apiInterface.fetchVideoList();

        call.enqueue(new Callback<YoutubeData>() {

            @Override
            public void onResponse(Call<YoutubeData> call, Response<YoutubeData> response) {
                progress.setVisibility(View.GONE);
//                Log.d("===Response", response.body().getNextPageToken());
                youtubeData = response.body();
                filterData();
            }
            @Override
            public void onFailure(Call<YoutubeData> call, Throwable t) {
                call.cancel();
                progress.setVisibility(View.GONE);
            }
        });
    }

   private void filterData(){
        if(youtubeData.getItems().size()>=youtubeData.getLimit()){
            ArrayList<Item> filterList = new ArrayList<>();
           for(int i = 0;i<youtubeData.getLimit();i++){
               filterList.add(youtubeData.getItems().get(i));
           }
           adapter.updateData(filterList);
           adapter.notifyDataSetChanged();
       }else{
           adapter.updateData(youtubeData.getItems());
           adapter.notifyDataSetChanged();
       }
    }
    
    @Override
    public void onClick(Item data) {
        Log.d("Data: ",data.getSnippet().getTitle());
        Intent intent = new Intent(this, VideoDetailActivity.class);
        intent.putExtra(Constants.videoId,data.getId().getVideoId());
        intent.putExtra(Constants.videoList,youtubeData);
        startActivity(intent);
    }
}
