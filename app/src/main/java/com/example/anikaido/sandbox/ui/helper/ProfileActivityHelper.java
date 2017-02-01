package com.example.anikaido.sandbox.ui.helper;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by anikaido on 2017/02/01.
 */

public class ProfileActivityHelper {
    final private String mePath = "/v2.5/me?fields=cover&locale=ja_JP";

    public Single<GraphResponse> getCover(AccessToken accessToken) {
        final GraphRequest graphRequest = new GraphRequest(accessToken, mePath);

        return Single.create(new Single.OnSubscribe<GraphResponse>() {
            @Override
            public void call(SingleSubscriber<? super GraphResponse> singleSubscriber) {
                singleSubscriber.onSuccess(graphRequest.executeAndWait());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
