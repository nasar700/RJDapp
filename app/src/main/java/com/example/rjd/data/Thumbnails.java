package com.example.rjd.data;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {
    @SerializedName("default")
    private Default defaults;

    public Default getDefaults() {
        return defaults;
    }

    public void setDefaults(Default defaults) {
        this.defaults = defaults;
    }
}
