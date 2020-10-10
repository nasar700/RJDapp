package com.laluyadav.rjd.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class YoutubeData implements Serializable {

    @SerializedName("limit")
    private int limit;

    @SerializedName("nextPageToken")
    private String nextPageToken;

    @SerializedName("items")
    private ArrayList<Item> items;


    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
