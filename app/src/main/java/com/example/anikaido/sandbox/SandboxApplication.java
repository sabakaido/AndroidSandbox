package com.example.anikaido.sandbox;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by anikaido on 2016/12/31.
 */

public class SandboxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
