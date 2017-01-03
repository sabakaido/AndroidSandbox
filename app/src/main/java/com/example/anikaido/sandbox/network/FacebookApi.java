package com.example.anikaido.sandbox.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anikaido on 2017/01/03.
 */

public class FacebookApi {
    final private String host = "graph.facebook.com";
    final private String mePath = "/v2.5/me";

    public String getAvatar() {
        Request request = new Request.Builder()
                .url(host + mePath)
                .get()
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(15 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print(response);
            }
        });

        return "";
    }
}
