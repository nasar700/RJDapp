package com.example.rjd.data;

import com.google.gson.annotations.SerializedName;

public class Default {
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
