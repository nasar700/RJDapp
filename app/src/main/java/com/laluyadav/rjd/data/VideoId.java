package com.laluyadav.rjd.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoId implements Serializable {
    @SerializedName("videoId")
    private String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
