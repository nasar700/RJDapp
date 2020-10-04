package com.laluyadav.rjd.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Default  implements Serializable {
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
