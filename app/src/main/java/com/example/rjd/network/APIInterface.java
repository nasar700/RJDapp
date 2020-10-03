package com.example.rjd.network;

import com.example.rjd.data.YoutubeData;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("youtube/v3/search")
    Call<YoutubeData> fetchVideoList(@Query("part") String part,
                                     @Query("channelId") String channelId,
                                     @Query("maxResults") String maxResults,
                                     @Query("key") String key,
                                     @Query("fields") String fields,
                                     @Query("order") String order);
}
