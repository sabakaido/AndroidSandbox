package com.example.anikaido.sandbox.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anikaido.sandbox.R;
import com.example.anikaido.sandbox.ui.helper.HomeActivityHelper;
import com.facebook.AccessToken;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.functions.Action1;

/**
 * Created by anikaido on 2016/12/31.
 */

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.accessTokenTextView)
    TextView mAccessTokenTextView;

    @BindView(R.id.profile_image)
    CircleImageView mCircleImageView;

    AccessToken mAccessToken;

    HomeActivityHelper mHomeActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandbox_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mAccessToken = (AccessToken) intent.getParcelableExtra("accesstoken");
        mAccessTokenTextView.setText(mAccessToken.getToken());

        mHomeActivityHelper = new HomeActivityHelper();

        final Activity activity = this;
        mHomeActivityHelper.getProfile(mAccessToken).subscribe(new Action1<GraphResponse>() {
            @Override
            public void call(GraphResponse graphResponse) {
                mAccessTokenTextView.setText(graphResponse.getRawResponse());

                    try {
                        JSONObject json = new JSONObject(graphResponse.getRawResponse());
                        String url = json.getJSONObject("picture").getJSONObject("data").getString("url");
                        Glide.with(activity)
                                .load(url)
                                .fitCenter()
                                .into(mCircleImageView);
                    } catch (Exception e) {
                        Log.d("hoge", e.getMessage());
                    }
            }
        });
    }
}
