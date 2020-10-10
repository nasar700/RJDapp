package com.laluyadav.rjd.network;

import com.laluyadav.rjd.data.YoutubeData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("getImageList")
    Call<ArrayList<String>> fetchImageList();

    @GET("getVideoList")
    Call<YoutubeData> fetchVideoList();
}
