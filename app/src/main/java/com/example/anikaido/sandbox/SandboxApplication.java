package com.example.anikaido.sandbox;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.facebook.FacebookSdk;

/**
 * Created by anikaido on 2016/12/31.
 */

public class SandboxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        Glide.setup(new GlideBuilder(this)
                .setMemoryCache(new LruResourceCache(10000000))
        );
    }
}
