package com.laluyadav.rjd.network;

import com.laluyadav.rjd.data.YoutubeData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {


//    @GET("youtube/v3/search")
//    Call<YoutubeData> fetchVideoList(@Query("part") String part,
//                                     @Query("channelId") String channelId,
//                                     @Query("maxResults") String maxResults,
//                                     @Query("key") String key,
//                                     @Query("fields") String fields,
//                                     @Query("order") String order);

    @GET("getImageList")
    Call<ArrayList<String>> fetchImageList();

    @GET("getVideoList")
    Call<YoutubeData> fetchVideoList();
}
