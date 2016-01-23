package com.zuowen.magic;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class MagicApplication extends Application {

    private Gson gson;
    private static MagicApplication mApplication;
    private     WifiManager wm;
    public static MagicApplication getApplication(){
        return mApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        gson = new GsonBuilder().create();
        wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
    }






    public boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] infos = connectivity.getAllNetworkInfo();
            for (NetworkInfo info : infos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        else {
            return false;
        }
        return false;
    }

    protected boolean isAppActive = false;

    public void OnAppInBackground()
    {
        isAppActive = false;
    }

    public void OnAppInForeground()
    {
        isAppActive = true;
    }
    public Gson getGson()
    {
        return gson;
    }


}
