package com.laluyadav.rjd.youtube;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laluyadav.rjd.R;
import com.laluyadav.rjd.data.Constants;
import com.laluyadav.rjd.data.Item;
import com.laluyadav.rjd.data.VideoClickListener;
import com.laluyadav.rjd.data.YoutubeData;
import com.laluyadav.rjd.home.HomeAdapter;
import com.laluyadav.rjd.network.CheckInternetConnection;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.laluyadav.rjd.utils.AdsManagerUtil;

public class VideoDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, VideoClickListener {

    boolean fullScreen = false;
    private ProgressDialog pDialog;
    private MyPlaybackEventListener playbackEventListener;
    private YouTubePlayer player;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private YouTubePlayerView youTubeView;
    private String videoId;

    @Override
    public void onClick(Item data) {
        VideoDetailActivity.this.player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        VideoDetailActivity.this.player.cueVideo(data.getId().getVideoId());
        VideoDetailActivity.this.player.play();
    }

    class C06932 implements YouTubePlayer.OnFullscreenListener {
        C06932() {
        }

        public void onFullscreen(boolean _isFullScreen) {
            VideoDetailActivity.this.fullScreen = _isFullScreen;
        }
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
        private MyPlaybackEventListener() {
        }

        public void onPlaying() {
            VideoDetailActivity.this.showMessage("Playing");
        }

        public void onPaused() {
            VideoDetailActivity.this.showMessage("Paused");
        }

        public void onStopped() {
            VideoDetailActivity.this.showMessage("Stopped");
        }

        public void onBuffering(boolean b) {
        }

        public void onSeekTo(int i) {
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        private MyPlayerStateChangeListener() {
        }

        public void onLoading() {
        }

        public void onLoaded(String s) {
            VideoDetailActivity.this.player.play();
        }

        public void onAdStarted() {
        }

        public void onVideoStarted() {
        }

        public void onVideoEnded() {
        }

        public void onError(YouTubePlayer.ErrorReason errorReason) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        AdsManagerUtil.showInterstitialAd();
        showBanner();

        showData();
        initYoutube();
    }

    private void showBanner(){
        LinearLayout adsMobBanner = findViewById(R.id.mainLayout);
        AdsManagerUtil.showAdMObBanner(this, adsMobBanner);
    }

    private void showData(){
        if(getIntent().getExtras() != null){
            videoId = getIntent().getExtras().getString(Constants.videoId);
            YoutubeData youtubeData = (YoutubeData) getIntent().getExtras().getSerializable(Constants.videoList);
            Log.d("===youtube",youtubeData.getNextPageToken());
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            HomeAdapter adapter = new HomeAdapter(youtubeData.getItems(), this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    protected void initYoutube(){
        this.pDialog = new ProgressDialog(this);
        this.pDialog.setMessage("Please wait...");
        this.pDialog.setCancelable(false);
        this.youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        this.youTubeView.initialize(Constants.key, this);
        this.playerStateChangeListener = new MyPlayerStateChangeListener();
        this.playbackEventListener = new MyPlaybackEventListener();
    }

    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        pDialog.dismiss();
        this.player = player;
        player.setPlayerStateChangeListener(this.playerStateChangeListener);
        player.setPlaybackEventListener(this.playbackEventListener);
        if (!new CheckInternetConnection().isNetworkAvailable(this)) {
            Toast.makeText(this, "Internet not Available,Please Check your Internet Connection", Toast.LENGTH_LONG).show();
        } else if (!wasRestored) {
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            player.cueVideo(videoId);
            player.play();
            player.setOnFullscreenListener(new C06932());
        }
    }

    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        pDialog.dismiss();
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, 1).show();
            return;
        }
        Toast.makeText(this,"error", Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            getYouTubePlayerProvider().initialize(Constants.key, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return this.youTubeView;
    }

    private void showMessage(String message) {
    }

    public void onBackPressed() {
        adLoadedAndRedirect();
    }

    public void adLoadedAndRedirect() {
        switch (getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
            case 3:
                this.player.setFullscreen(false);
                return;
            default:
                super.onBackPressed();
                return;
        }
    }
}

