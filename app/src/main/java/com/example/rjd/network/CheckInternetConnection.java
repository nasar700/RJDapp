package com.example.rjd.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnection {
    boolean isNetwork = true;

    public boolean isNetworkAvailable(Context context) {

        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            this.isNetwork = false;
        } else {
            this.isNetwork = true;
        }
        return this.isNetwork;
    }
}
