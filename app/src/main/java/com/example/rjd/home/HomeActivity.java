package com.example.rjd.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rjd.R;
import com.example.rjd.data.ClickListener;
import com.example.rjd.data.Constants;
import com.example.rjd.data.Item;
import com.example.rjd.data.YoutubeData;
import com.example.rjd.more.MoreActivity;
import com.example.rjd.network.APIClient;
import com.example.rjd.network.APIInterface;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ClickListener {

    private APIInterface apiInterface;
    private Button button;
    private HomeAdapter adapter;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        fetchVideoData();
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
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<YoutubeData> call = apiInterface.fetchVideoList(Constants.part,
                Constants.channelId, Constants.maxResults_20,
                Constants.key, Constants.fields, Constants.order);

        call.enqueue(new Callback<YoutubeData>() {

            @Override
            public void onResponse(Call<YoutubeData> call, Response<YoutubeData> response) {
                progress.setVisibility(View.GONE);
                Log.d("===Response", response.body().getNextPageToken());
                adapter.updateData(response.body().getItems());
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
        //Log.d("Data: ",data.getTitle());
    }
}
