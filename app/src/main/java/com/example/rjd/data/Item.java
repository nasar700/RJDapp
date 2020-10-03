package com.example.rjd.data;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private VideoId id;

    @SerializedName("snippet")
    private Snippet snippet;

    public VideoId getId() {
        return id;
    }

    public void setId(VideoId id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
